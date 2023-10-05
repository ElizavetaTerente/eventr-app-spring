package at.qe.skeleton.services;

import at.qe.skeleton.model.*;
import at.qe.skeleton.repositories.VotingRepository;
import at.qe.skeleton.ui.beans.SessionInfoBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
@Scope("application")
public class VotingService {

    @Autowired
    SessionInfoBean sessionInfoBean;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private VotingRepository votingRepository;

    @Autowired
    private MailService mailService;

    public List<Voting> getAllVotings() {
        return votingRepository.findAll();
    }

    public List<Voting> getExpiredVotings() {
        return votingRepository.findExpiredVotings();
    }

    public List<Voting> getAllActiveVotings() {
        return votingRepository.findVotingsByStatus(true);
    }

    public List<Voting> getAllActiveVotings(User user) {
        return votingRepository.findActiveVotingsByUser(user);
    }

    // Load Voting from DB
    public Voting loadVoting(String votingName) {
        return votingRepository.findVotingByName(votingName);
    }

    // Save Voting to DB
    public Voting saveVoting(Voting voting) {
        if (voting.isNew()) {
            voting.setCreateDate(new Date());
            voting.setCreateUser(userService.getAuthenticatedUser());
        } else {
            voting.setUpdateDate(new Date());
        }
        return votingRepository.save(voting);
    }

    public void createVoting(Voting voting) {
        Date now = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Calendar sub = Calendar.getInstance();
        sub.setTime(now);
        sub.add(Calendar.HOUR, 24);
        Date end = sub.getTime();

        voting.getParticipants().add(sessionInfoBean.getCurrentUser());
        voting.setVotingStop(end);
        voting.setVotingActive(true);

        votingRepository.save(voting);
    }

    public Voting addVote(Voting voting, Location location, Integer weight) {
        return votingRepository.save(voting);
    }

    // Delete Voting from DB id user is either Admin or created the voting
    public void deleteVoting(Voting voting) {
        votingRepository.delete(voting);
    }

    // Check if votings run out every second
    // If yes and voting marked as active call functions in votingEndCalls()
    // then set voting inactive
    @Scheduled(cron = "*/5 * * * * ?")
    @Transactional
    public void checkForExpiredVotings() {
        List<Voting> votings = this.getExpiredVotings();

        if (votings != null) {
            for (Voting voting : votings) {
                this.endVoting(voting);
            }
        }
    }

    public void endVoting(Voting voting) {
        // endvoting
        voting.setVotingActive(false);
        this.saveVoting(voting);

        // initialize event
        int helper = 0;

        Event event = new Event();
        event.setName(voting.getName());
        Timeslot winner_time;
        Location winner_location;
        Random rand = new Random(System.currentTimeMillis());

        // set participants
        event.setParticipants(voting.getParticipants());
        event.setNumParticipants(event.getParticipants().size());

        // if nobody voted just pick a random location/timeslot
        if (voting.getVotes() == null) {
            int timeslotRand = rand.nextInt(voting.getTimeslots().size());
            int locationRand = rand.nextInt(voting.getPossibilities().size());

            System.out.println(timeslotRand);
            System.out.println(locationRand);

            winner_time = voting.getTimeslots().get(rand.nextInt(voting.getTimeslots().size()));
            winner_location = voting.getPossibilities().get(rand.nextInt(voting.getPossibilities().size()));

            event.setStart(winner_time.getStart());
            event.setEnd(winner_time.getEnd());
            event.setLocation(winner_location);
            event.setCreateUser(voting.getCreateUser());
            event.setCreateDate(new Date());
            event.setUpdateUser(event.getCreateUser());
            event.setUpdateDate(event.getCreateDate());
            eventService.saveEvent(event);
            return;
        }

        // timeslots
        if (voting.getTimeslots().size() > 0) {
            // initialize sum up array for timeslots
            List<VoteTimeslot> vote_time_sum = new ArrayList<VoteTimeslot>();
            for (Timeslot timeslot : voting.getTimeslots()) {
                VoteTimeslot voteTimeslot = new VoteTimeslot();
                voteTimeslot.setTimeslot(timeslot);
                voteTimeslot.setWeight(0);
                vote_time_sum.add(voteTimeslot);
            }

            // count the timeslot specific votes
            for (Vote vote : voting.getVotes()) {
                for (VoteTimeslot target_voted_timeslot : vote_time_sum) {
                    for (VoteTimeslot user_voted_timeslot : vote.getVoteTimeslots()) {
                        if (user_voted_timeslot.getTimeslot().getStart()
                                .equals(target_voted_timeslot.getTimeslot().getStart())
                                && user_voted_timeslot.getTimeslot().getEnd()
                                        .equals(target_voted_timeslot.getTimeslot().getEnd())) {
                            target_voted_timeslot
                                    .setWeight(target_voted_timeslot.getWeight() + user_voted_timeslot.getWeight());
                        }
                    }
                }
            }

            // determine the timeslot winner
            helper = vote_time_sum.get(0).getWeight();
            winner_time = vote_time_sum.get(0).getTimeslot();
            for (VoteTimeslot target_voted_timeslot : vote_time_sum) {
                if (target_voted_timeslot.getWeight() > helper) {
                    helper = target_voted_timeslot.getWeight();
                    winner_time = target_voted_timeslot.getTimeslot();
                } else if (target_voted_timeslot.getWeight() == helper && rand.nextBoolean()) {
                    winner_time = target_voted_timeslot.getTimeslot();
                }
            }
            event.setStart(winner_time.getStart());
            event.setEnd(winner_time.getEnd());
        } else {
            winner_time = null;
            event.setStart(null);
            event.setEnd(null);
        }

        // location
        if (voting.getPossibilities().size() > 0) {
            // initialize sum up array for locations
            List<VoteLocation> vote_location_sum = new ArrayList<VoteLocation>();
            for (Location location : voting.getPossibilities()) {
                VoteLocation voteLocation = new VoteLocation();
                voteLocation.setLocation(location);
                voteLocation.setWeight(0);
                vote_location_sum.add(voteLocation);
            }

            // count the location specific votes
            for (Vote vote : voting.getVotes()) {
                for (VoteLocation target_voted_location : vote_location_sum) {
                    for (VoteLocation user_voted_location : vote.getVoteLocations()) {
                        if (user_voted_location.getLocation().getName()
                                .equals(target_voted_location.getLocation().getName())) {
                            target_voted_location
                                    .setWeight(target_voted_location.getWeight() + user_voted_location.getWeight());
                        }
                    }
                }
            }

            // determine the location winner
            helper = vote_location_sum.get(0).getWeight();
            winner_location = vote_location_sum.get(0).getLocation();
            for (VoteLocation target_voted_location : vote_location_sum) {
                if (target_voted_location.getWeight() > helper) {
                    helper = target_voted_location.getWeight();
                    winner_location = target_voted_location.getLocation();
                } else if (target_voted_location.getWeight() == helper && rand.nextBoolean()) {
                    winner_location = target_voted_location.getLocation();
                }
            }
        } else {
            winner_location = null;
        }

        // create the event
        event.setLocation(winner_location);
        event.setCreateUser(voting.getCreateUser());
        event.setCreateDate(new Date());
        event.setUpdateUser(event.getCreateUser());
        event.setUpdateDate(event.getCreateDate());
        eventService.saveEvent(event);

        mailService.sendMailEnded(event);
    }

    public void cancel(Voting voting) {
        String name = voting.getName();
        String[] tos = voting.getParticipants().stream().map(x -> x.getEmail()).collect(Collectors.toList())
                .toArray(new String[0]);
        mailService.sendMailCancelled(name, tos);

        voting.cancel();
        this.deleteVoting(voting);
    }

}

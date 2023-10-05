package at.qe.skeleton.tests;

import at.qe.skeleton.model.*;
import at.qe.skeleton.repositories.VotingRepository;
import at.qe.skeleton.services.LocationService;
import at.qe.skeleton.services.UserService;
import at.qe.skeleton.services.VotingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@SpringBootTest
@WebAppConfiguration
public class VotingServiceTest {

        @Autowired
        VotingService votingService;

        @Autowired
        VotingRepository votingRepository;

        @Autowired
        UserService userService;

        @Autowired
        LocationService locationService;

        @Test
        @WithMockUser(username = "admin", authorities = { "ADMIN" })
        public void datainitializationTest() {
                Assertions.assertEquals(3, votingService.getAllVotings().size(),
                                "Insufficient amount of voting initialized for test data source");
                Assertions.assertEquals(3, votingService.getAllActiveVotings().size());
                Assertions.assertEquals(1, votingService.getAllActiveVotings(userService.loadUser("liza")).size());
                for (Voting voting : votingService.getAllVotings()) {
                        if ("Ablschlussessen".equals(voting.getName())) {
                                Assertions.assertEquals(3, voting.getParticipants().size(),
                                                "Voting \"" + voting + "\" does not have right number of participants");
                                Assertions.assertEquals(3, voting.getPossibilities().size(),
                                                "Voting \"" + voting
                                                                + "\" does not have right number of possibilities");
                                Assertions.assertNotNull(voting.getCreateUser(),
                                                "Voting \"" + voting + "\" does not have a createUser defined");
                                Assertions.assertTrue(voting.isVotingActive());
                                Assertions.assertEquals(0, voting.getVotes().size());
                                Assertions.assertEquals("Ablschlussessen", voting.getId());
                        } else if ("Morgensemmel 13".equals(voting.getName())) {
                                Assertions.assertEquals(3, voting.getParticipants().size(),
                                                "Voting \"" + voting + "\" does not have right number of participants");
                                Assertions.assertEquals(1, voting.getPossibilities().size(),
                                                "Voting \"" + voting
                                                                + "\" does not have right number of possibilities");
                                Assertions.assertNotNull(voting.getCreateUser(),
                                                "Voting \"" + voting + "\" does not have a createUser defined");
                                Assertions.assertTrue(voting.isVotingActive());
                        } else if ("DIRTY_HARRY".equals(voting.getName())) {
                                Assertions.assertEquals(0, voting.getParticipants().size(),
                                                "Voting \"" + voting + "\" does not have right number of participants");
                                Assertions.assertEquals(0, voting.getPossibilities().size(),
                                                "Voting \"" + voting
                                                                + "\" does not have right number of possibilities");
                                Assertions.assertNotNull(voting.getCreateUser(),
                                                "Voting \"" + voting + "\" does not have a createUser defined");
                                Assertions.assertFalse(voting.isVotingActive());
                        } else if ("sektfrühstück".equals(voting.getName())) {
                                Assertions.assertEquals(1, voting.getParticipants().size(),
                                                "Voting \"" + voting + "\" does not have right number of participants");
                                Assertions.assertEquals(0, voting.getPossibilities().size(),
                                                "Voting \"" + voting
                                                                + "\" does not have right number of possibilities");
                                Assertions.assertNotNull(voting.getCreateUser(),
                                                "Voting \"" + voting + "\" does not have a createUser defined");
                                Assertions.assertTrue(voting.isVotingActive());
                        } else if ("party".equals(voting.getName())) {
                                Assertions.assertEquals(3, voting.getParticipants().size(),
                                                "Voting \"" + voting + "\" does not have right number of participants");
                                Assertions.assertEquals(3, voting.getPossibilities().size(),
                                                "Voting \"" + voting
                                                                + "\" does not have right number of possibilities");
                                Assertions.assertNotNull(voting.getCreateUser(),
                                                "Voting \"" + voting + "\" does not have a createUser defined");
                                Assertions.assertTrue(voting.isVotingActive());
                        } else {
                                Assertions.fail("Unknown  \"" + voting.getName() + "\" loaded from test data source ");
                        }
                }

        }

        @Test
        @WithMockUser(username = "admin", authorities = { "ADMIN" })
        public void CreateAndSafeVotingTest() {

                Timeslot timeslot = new Timeslot();
                Date date = new Date(2022, 01, 3);
                LocalDateTime start = LocalDateTime.of(2022, 02, 01, 18, 00);
                LocalDateTime end = LocalDateTime.of(2022, 02, 01, 20, 00);
                timeslot.setStart(start);
                timeslot.setEnd(end);
                Assertions.assertEquals(start, timeslot.getStart());
                Assertions.assertEquals(end, timeslot.getEnd());

                VoteTimeslot voteTimeslot = new VoteTimeslot();
                voteTimeslot.setTimeslot(timeslot);
                voteTimeslot.setWeight(5);

                Assertions.assertEquals(timeslot, voteTimeslot.getTimeslot());
                Assertions.assertEquals(5, voteTimeslot.getWeight());

                VoteLocation voteLocation = new VoteLocation();
                voteLocation.setLocation(locationService.loadLocation("Mensa Technik"));
                voteLocation.setWeight(5);

                Assertions.assertEquals("Mensa Technik", voteLocation.getLocation().getName());
                Assertions.assertEquals(5, voteLocation.getWeight());

                Vote vote = new Vote();
                vote.setUpdateDate(date);
                // List voteTimeslotList = new ArrayList();
                // voteTimeslotList.add(voteTimeslot);

                // List voteLocationList = new ArrayList();
                // voteLocationList.add(voteLocation);

                // vote.setVoteLocations(voteLocationList);
                // vote.setVoteTimeslots(voteTimeslotList);

                vote.setCreateDate(date);
                vote.setCreateUser(userService.loadUser("liza"));
                Long id = 123L;
                vote.setId(id);

                Assertions.assertEquals(date, vote.getUpdateDate());
                Assertions.assertEquals(date, vote.getCreateDate());
                Assertions.assertEquals("liza", vote.getCreateUser().getUsername());
                Assertions.assertFalse(vote.isNew());
                Assertions.assertEquals(id, vote.getId());

                String votingname = "testVoting";
                boolean active = true;
                Date stop = new Date(2022, 01, 22, 17, 30);

                Voting toBeCreatedVoting = new Voting();
                toBeCreatedVoting.setName(votingname);
                toBeCreatedVoting.setVotingStop(stop);
                toBeCreatedVoting.setVotingStop(stop);
                User loadedUser = userService.loadUser("liza");
                toBeCreatedVoting.setCreateUser(loadedUser);
                toBeCreatedVoting.setVotingActive(true);
                toBeCreatedVoting.setUpdateUser(loadedUser);
                toBeCreatedVoting.setUpdateDate(stop);
                List tll = new ArrayList<>();
                tll.add(timeslot);
                toBeCreatedVoting.setTimeslots(tll);
                List vl = new ArrayList();
                vl.add(vote);
                toBeCreatedVoting.setVotes(vl);
                List ll = new ArrayList<>();
                Location loadedLocation = locationService.loadLocation("Mensa");
                ll.add(loadedLocation);
                toBeCreatedVoting.setPossibilities(ll);

                List pl = new ArrayList<>();
                pl.add(userService.loadUser("test_user"));
                toBeCreatedVoting.setParticipants(pl);

                votingService.saveVoting(toBeCreatedVoting);

                vote.setVoting(toBeCreatedVoting);
                Assertions.assertEquals(toBeCreatedVoting, vote.getVoting());

                Voting freshlyCreatedVoting = votingService.loadVoting(votingname);
                Assertions.assertNotNull(freshlyCreatedVoting,
                                "New voting could not be loaded from test data source after being saved");
                Assertions.assertEquals(votingname, freshlyCreatedVoting.getName(),
                                "New voting could not be loaded from test data source after being saved");
                Assertions.assertNotNull(freshlyCreatedVoting.getCreateDate(),
                                "voting \"" + votingname + "\" does not have a createDate defined after being saved");
                Assertions.assertEquals(4, votingService.getAllVotings().size(),
                                "Insufficient amount of votings initialized for test data source");
                Assertions.assertEquals(stop, freshlyCreatedVoting.getVotingStop());
                Assertions.assertEquals(loadedUser.getUsername(), freshlyCreatedVoting.getUpdateUser().getUsername());
                Assertions.assertEquals(stop, freshlyCreatedVoting.getUpdateDate());

        }

        @DirtiesContext
        @Test
        @WithMockUser(username = "liza", authorities = { "ADMIN" })
        public void deleteVotingAsAdminTest() {
                String votingName = "Ablschlussessen";
                Voting toBeDeletedVoting = votingService.loadVoting(votingName);
                Assertions.assertNotNull(toBeDeletedVoting,
                                "Voting \"" + votingName + "\" could not be loaded from test data source");
                votingService.deleteVoting(toBeDeletedVoting);

                Assertions.assertEquals(2, votingService.getAllVotings().size(), "No voting has been deleted ");
        }

        @DirtiesContext
        @Test
        @WithMockUser(username = "manager", authorities = { "MANAGER" })
        public void deleteVotingAsManagerTest() {

                // Assertions.assertThrows(java.lang.NullPointerException.class, () -> {

                String votingName = "Ablschlussessen";
                Voting toBeDeletedVoting = votingService.loadVoting(votingName);
                Assertions.assertNotNull(toBeDeletedVoting,
                                "Voting \"" + votingName + "\" could not be loaded from test data source");
                votingService.deleteVoting(toBeDeletedVoting);

                Assertions.assertEquals(2, votingService.getAllVotings().size(), "No voting has been deleted ");
                // });
        }

        @DirtiesContext
        @Test
        @WithMockUser(username = "user", authorities = { "USER" })
        public void deleteVotingAsUserTest() {
                // Assertions.assertThrows(java.lang.NullPointerException.class, () -> {

                String votingName = "Ablschlussessen";
                Voting toBeDeletedVoting = votingService.loadVoting(votingName);
                Assertions.assertNotNull(toBeDeletedVoting,
                                "Voting \"" + votingName + "\" could not be loaded from test data source");
                votingService.deleteVoting(toBeDeletedVoting);

                Assertions.assertEquals(2, votingService.getAllVotings().size(), "No voting has been deleted ");
                // });
        }

        @DirtiesContext
        @Test
        @WithMockUser(username = "liza", authorities = { "ADMIN" })
        public void createVotingAddVoteEndVotingTest() {

                String votingname = "testVoting";
                boolean active = true;
                Date stop = new Date(2022, 01, 22, 17, 30);

                Voting toBeCreatedVoting = new Voting();
                toBeCreatedVoting.setName(votingname);
                toBeCreatedVoting.setVotingStop(stop);
                toBeCreatedVoting.setVotingStop(stop);
                User loadedUser = userService.loadUser("liza");
                toBeCreatedVoting.setCreateUser(loadedUser);
                toBeCreatedVoting.setVotingActive(true);
                toBeCreatedVoting.setUpdateUser(loadedUser);
                toBeCreatedVoting.setUpdateDate(stop);
                List ll = new ArrayList<>();
                Location loadedLocation = locationService.loadLocation("Mensa");
                ll.add(loadedLocation);
                toBeCreatedVoting.setPossibilities(ll);

                List ll2 = new ArrayList<>();
                Timeslot timeslot = new Timeslot();
                ll2.add(timeslot);
                toBeCreatedVoting.setTimeslots(ll2);

                votingService.createVoting(toBeCreatedVoting);
                votingService.addVote(toBeCreatedVoting, locationService.loadLocation("Mensa"), 6);
                votingService.endVoting(toBeCreatedVoting);
                Assertions.assertFalse(toBeCreatedVoting.isVotingActive());

        }

        /*
         * @DirtiesContext
         * 
         * @Test
         * 
         * @WithMockUser(username = "user_test", authorities = {"USER"})
         * public void deleteVotingAsCurrentUserTest() {
         * 
         * String votingName = "testVoting";
         * boolean active = true;
         * Date stop = new Date(2022,01,22,17,30);
         * 
         * Voting toBeCreatedVoting = new Voting();
         * toBeCreatedVoting.setName(votingName);
         * toBeCreatedVoting.setVotingActive(active);
         * toBeCreatedVoting.setVotingStop(stop);
         * 
         * votingService.saveVoting(toBeCreatedVoting);
         * Voting toBeDeletedVoting = votingService.loadVoting(votingName);
         * Assertions.assertNotNull(toBeDeletedVoting, "Voting \"" + votingName +
         * "\" could not be loaded from test data source");
         * System.out.println(toBeDeletedVoting.getCreateUser().getUsername());
         * votingService.deleteVoting(toBeDeletedVoting);
         * 
         * Assertions.assertEquals(3, votingService.getAllVotings().size(),
         * "No voting has been deleted ");
         * 
         * }
         * 
         */

}

package at.qe.skeleton.ui.controllers;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

import at.qe.skeleton.services.EventService;
import at.qe.skeleton.services.VotingService;
import at.qe.skeleton.ui.beans.SessionInfoBean;

import at.qe.skeleton.model.*;
import at.qe.skeleton.repositories.VotingRepository;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

@Component
@Scope("view")
public class VotingWizard implements Serializable {

    @Autowired
    SessionInfoBean sessionInfoBean;

    @Autowired
    VotingService votingService;

    @Autowired
    EventService eventService;

    @Autowired
    VotingRepository votingRepository;

    private String name;

    private LocalDateTime open;
    private LocalTime close;

    private Location selectedLocation;
    private List<Location> selectedLocations;

    private User selectedParticipant;
    private List<User> selectedParticipants;

    private Voting voting;

    private List<Timeslot> timeslots;

    private LocalDateTime timeslotStart;
    private LocalDateTime timeslotEnd;
    private String timeslotSlider;

    @PostConstruct
    public void init() {
        selectedLocations = new ArrayList<>();
        selectedParticipants = new ArrayList<>();
        timeslots = new ArrayList<>();
        timeslotSlider = "1";
    }

    public String getTimeslotSlider() {
        return timeslotSlider;
    }

    public void setTimeslotSlider(String timeslotSlider) {
        this.timeslotSlider = timeslotSlider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getSelectedLocation() {
        return selectedLocation;
    }

    public void setSelectedLocation(Location selectedLocation) {
        this.selectedLocation = selectedLocation;
    }

    public List<Location> getSelectedLocations() {
        return selectedLocations;
    }

    public void setSelectedLocations(List<Location> selectedLocations) {
        this.selectedLocations = selectedLocations;
    }

    public Voting getVoting() {
        return voting;
    }

    public void setVoting(Voting voting) {
        this.voting = voting;
    }

    public User getSelectedParticipant() {
        return selectedParticipant;
    }

    public void setSelectedParticipant(User selectedParticipant) {
        this.selectedParticipant = selectedParticipant;
    }

    public List<User> getSelectedParticipants() {
        return selectedParticipants;
    }

    public void setSelectedParticipants(List<User> selectedParticipants) {
        this.selectedParticipants = selectedParticipants;
    }

    public List<Timeslot> getTimeslots() {
        return timeslots;
    }

    public void setTimeslots(List<Timeslot> timeslots) {
        this.timeslots = timeslots;
    }

    public LocalDateTime getTimeslotStart() {
        return timeslotStart;
    }

    public void setTimeslotStart(LocalDateTime timeslotStart) {
        this.timeslotStart = timeslotStart;
    }

    public LocalDateTime getTimeslotEnd() {
        return timeslotEnd;
    }

    public void setTimeslotEnd(LocalDateTime timeslotEnd) {
        this.timeslotEnd = timeslotEnd;
    }

    public void addTimeslot() {
        if (timeslotStart != null && timeslotSlider != null) {
            Timeslot newTimeslot = new Timeslot();
            newTimeslot.setStart(timeslotStart);
            newTimeslot.setEnd(timeslotStart.plusHours(Integer.parseInt(timeslotSlider)));

            for (Timeslot checkTimeslot : this.timeslots) {
                if (checkTimeslot.getStart().equals(newTimeslot.getStart())
                        && checkTimeslot.getEnd().equals(newTimeslot.getEnd())) {
                    return;
                }
            }

            this.timeslots.add(newTimeslot);
        }
    }

    public void deleteTimeslot(Timeslot timeslot) {
        this.timeslots.remove(timeslot);
    }

    public void doSaveVoting() {
        Voting voting = new Voting();
        voting.setPossibilities(this.selectedLocations);
        voting.setTimeslots(this.timeslots);

        System.out.println(voting.getTimeslots());

        voting.getParticipants().add(sessionInfoBean.getCurrentUser());

        Date now = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Calendar sub = Calendar.getInstance();
        sub.setTime(now);
        sub.add(Calendar.HOUR, 24);
        Date end = sub.getTime();

        voting.setName(this.name);
        voting.setVotingStop(end);
        voting.setCreateDate(now);
        voting.setVotingActive(true);
        voting.setCreateUser(sessionInfoBean.getCurrentUser());

        votingService.saveVoting(voting);

        name = "";
        selectedLocations = new ArrayList<>();
        timeslots = new ArrayList<>();

        timeslotStart = null;
        timeslotEnd = null;
        timeslotSlider = null;

    }

    public boolean namesNotTaken() {
        if (this.name == null) {
            return false;
        }
        if (this.name.equals("")) {
            return false;
        }
        List<Event> eventsToCheck = eventService.getAllEvents();
        List<Voting> votingsToCheck = votingService.getAllVotings();
        for (Event event : eventsToCheck) {
            if (this.name.equals(event.getName())) {
                return false;
            }
        }
        for (Voting voting : votingsToCheck) {
            if (this.name.equals(voting.getName())) {
                return false;
            }
        }

        return true;
    }

    public LocalDateTime eventEarlyMin() {
        LocalDateTime now = LocalDateTime.now().plusHours(36);
        return now;
    }

    public void showInfo() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "New Voting - Info",
                "The voting will last for 1 day. \nThe earliest possilbe time for an event is 36 hours after now. \nYou have to choose at least one Timeslot and one Location. \nYour event has to have an unique name. \nYou as the Creator cannot leave the event/voting. \nThe Creator is the only one to invite others.");

        PrimeFaces.current().dialog().showMessageDynamic(message);
    }

    public void showInfoInvite() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "New Voting - Info",
                "Set the voting participants. \nOnly selected users and you take part and can vote. \nNew participants will receive an invite-mail. \nTo disinvite a user, call this dialog again and just not select him/her. \nYou as the Creator cannot leave the voting. \nYou as the Creator are the only one to invite others.");

        PrimeFaces.current().dialog().showMessageDynamic(message);
    }

}

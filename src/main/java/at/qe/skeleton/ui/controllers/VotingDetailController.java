package at.qe.skeleton.ui.controllers;

import at.qe.skeleton.model.*;
import at.qe.skeleton.repositories.LocationRepository;
import at.qe.skeleton.services.MailService;
import at.qe.skeleton.services.UserService;
import at.qe.skeleton.services.VoteService;
import at.qe.skeleton.services.VotingService;
import at.qe.skeleton.ui.beans.SessionInfoBean;

import org.primefaces.event.ReorderEvent;
import org.primefaces.extensions.component.legend.Legend;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Component
@Scope("view")
public class VotingDetailController implements Serializable {

    @Autowired
    private MailService mailService;

    @Autowired
    private SessionInfoBean sessionInfoBean;

    @Autowired
    private VotingService votingService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private UserService userService;

    @Autowired
    private LocationRepository locationRepository;

    private Voting voting;

    private BarChartModel barModelLocation;

    private LocalTime timeslotStart;
    private LocalTime timeslotEnd;
    private Timeslot timeslot;
    private List<Timeslot> timeslots;

    private List<Location> locations;

    private User selectedUser;
    private List<User> selectedUsers;

    @PostConstruct
    public void init() {
        locations = new ArrayList<>();
        timeslots = new ArrayList<>();
        timeslot = new Timeslot();
    }

    public void setVoting(Voting voting) {
        this.voting = voting;
        locations = voting.getPossibilities();
    }

    public Voting getVoting() {
        return voting;
    }

    public List<Location> getLocations(Voting voting) {
        return voting.getPossibilities();
    }

    public List<Location> getLocations() {
        return voting.getPossibilities();
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<User> getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(List<User> selectedUsers) {
        this.selectedUsers = selectedUsers;
    }

    public void inviteSelected() {
        mailService.sendMailInvitation(this.voting, selectedUsers);

        if (!selectedUsers.contains(sessionInfoBean.getCurrentUser())) {
            selectedUsers.add(sessionInfoBean.getCurrentUser());
        }

        voting.setParticipants(selectedUsers);
        votingService.saveVoting(voting);
    }

    public void endVoting(Voting voting) {
        votingService.endVoting(voting);
    }

    public void signOut() {
        if (!voting.getCreateUser().equals(sessionInfoBean.getCurrentUser())) {
            voting.getParticipants().remove(sessionInfoBean.getCurrentUser());
            votingService.saveVoting(voting);
        }
    }

    public void save() {

        boolean hasVoted = false;

        for (Vote vote : this.voting.getVotes()) {
            if (vote.getCreateUser().equals(sessionInfoBean.getCurrentUser())) {
                hasVoted = true;

                List<VoteTimeslot> timslotsVotingList = new ArrayList<VoteTimeslot>();
                List<VoteLocation> locationsVotingList = new ArrayList<VoteLocation>();

                for (Timeslot timeslot : this.voting.getTimeslots()) {
                    VoteTimeslot vt = new VoteTimeslot();
                    vt.setWeight(this.voting.getTimeslots().size() -
                            this.voting.getTimeslots().indexOf(timeslot));
                    vt.setTimeslot(timeslot);
                    timslotsVotingList.add(vt);
                }
                vote.setVoteTimeslots(timslotsVotingList);

                for (Location location : this.voting.getPossibilities()) {
                    VoteLocation vl = new VoteLocation();
                    vl.setWeight(this.voting.getPossibilities().size() -
                            this.voting.getPossibilities().indexOf(location));
                    vl.setLocation(location);
                    locationsVotingList.add(vl);
                }
                vote.setVoteLocations(locationsVotingList);
            }
        }

        if (!hasVoted) {
            Vote vote = new Vote();
            vote.setCreateUser(sessionInfoBean.getCurrentUser());
            vote.setVoting(voting);

            for (Timeslot timeslot : this.voting.getTimeslots()) {
                VoteTimeslot vt = new VoteTimeslot();
                vt.setWeight(this.voting.getTimeslots().size() -
                        this.voting.getTimeslots().indexOf(timeslot));
                vt.setTimeslot(timeslot);
                vote.getVoteTimeslots().add(vt);
            }

            for (Location location : this.voting.getPossibilities()) {
                VoteLocation vl = new VoteLocation();
                vl.setWeight(this.voting.getPossibilities().size() -
                        this.voting.getPossibilities().indexOf(location));
                vl.setLocation(location);
                vote.getVoteLocations().add(vl);
            }

            voting.getVotes().add(vote);
        }

        votingService.saveVoting(voting);

    }

    public void timeslotRanking(ReorderEvent event) {
        int startindex = event.getFromIndex();
        int endindex = event.getToIndex();

        Timeslot holder1 = this.voting.getTimeslots().get(startindex);
        Timeslot holder2 = this.voting.getTimeslots().get(endindex);

        // this swap seems wrong, and i tried it many times the "right" way, but only
        // this way it actually works

        this.voting.getTimeslots().set(startindex, holder1);
        this.voting.getTimeslots().set(endindex, holder2);

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rated",
                "Rated " + holder1.getStart() + " to " + holder1.getEnd() + " with "
                        + (this.voting.getTimeslots().size() - startindex)
                        + " Points and "
                        + holder2.getStart() + " to " + holder2.getEnd()
                        + " with " + (this.voting.getTimeslots().size() - endindex) + " Points");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void locationRanking(ReorderEvent event) {
        int startindex = event.getFromIndex();
        int endindex = event.getToIndex();

        Location holder1 = this.voting.getPossibilities().get(startindex);
        Location holder2 = this.voting.getPossibilities().get(endindex);

        // this swap seems wrong, and i tried it many times the "right" way, but only
        // this way it actually works

        this.voting.getPossibilities().set(startindex, holder1);
        this.voting.getPossibilities().set(endindex, holder2);

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rated",
                "Rated " + holder1.getName() + " with " + (this.voting.getPossibilities().size() - startindex)
                        + " Points and "
                        + holder2.getName()
                        + " with " + (this.voting.getPossibilities().size() - endindex) + " Points");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void createBarModelLocation() {
        barModelLocation = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Location Votes");

        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        if (voting == null) {
            values.add(10);
            labels.add("None");
            barDataSet.setData(values);
        } else {

            List<VoteLocation> tmp = voting.currentVotingStatusForLocations();
            if (tmp != null) {
                for (VoteLocation location : tmp) {
                    values.add(location.getWeight());
                    labels.add(location.getLocation().getName());
                }
            }
            barDataSet.setData(values);
        }

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        data.setLabels(labels);
        barModelLocation.setData(data);

        // Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Total Votes");
        options.setTitle(title);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barModelLocation.setOptions(options);
    }

    public BarChartModel getBarModelLocation() {
        createBarModelLocation();
        return barModelLocation;
    }

    public void setBarModelLocation(BarChartModel barModel) {
        this.barModelLocation = barModel;
    }

    public void createBarModelTime() {
        barModelLocation = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Location Votes");

        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        if (voting == null) {
            values.add(10);
            labels.add("None");
            barDataSet.setData(values);
        } else {

            List<VoteTimeslot> tmp = voting.currentVotingStatusForTime();
            if (tmp != null) {
                for (VoteTimeslot timeslot : tmp) {
                    values.add(timeslot.getWeight());
                    labels.add(timeslot.toString());
                }
            }
            barDataSet.setData(values);
        }

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        data.setLabels(labels);
        barModelLocation.setData(data);

        // Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Total Votes");
        options.setTitle(title);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barModelLocation.setOptions(options);
    }

    public BarChartModel getBarModelTime() {
        createBarModelTime();
        return barModelLocation;
    }

    public void setBarModelTime(BarChartModel barModel) {
        this.barModelLocation = barModel;
    }

    public void cancel() {
        votingService.cancel(voting);
    }

}
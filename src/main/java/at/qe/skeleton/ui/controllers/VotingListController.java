package at.qe.skeleton.ui.controllers;

import at.qe.skeleton.model.Voting;
import at.qe.skeleton.services.UserService;
import at.qe.skeleton.services.VotingService;
import at.qe.skeleton.ui.beans.SessionInfoBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

@Component
@Scope("view")
public class VotingListController implements Serializable {

    @Autowired
    private VotingService votingService;

    @Autowired
    private UserService userService;

    private List<Voting> votings;

    @PostConstruct
    public void init() {
        this.votings = votingService.getAllActiveVotings();
    }

    public List<Voting> getVotings() {
        return votingService.getAllVotings();
    }

    public List<Voting> getAllActiveVotings() {
        return votingService.getAllActiveVotings();
    }

    public List<Voting> getUserSpecificVotings() {
        return votingService.getAllActiveVotings(userService.getAuthenticatedUser());
    }

}

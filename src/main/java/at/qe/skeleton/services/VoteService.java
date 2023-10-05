package at.qe.skeleton.services;

import at.qe.skeleton.model.*;
import at.qe.skeleton.repositories.VoteRepository;
import at.qe.skeleton.repositories.VotingRepository;
import at.qe.skeleton.ui.beans.SessionInfoBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
@Scope("application")
public class VoteService {

    @Autowired
    SessionInfoBean sessionInfoBean;

    @Autowired
    private UserService userService;

    @Autowired
    private VoteRepository voteRepository;

    public Vote saveVote(Vote vote) {
        if (vote.isNew()) {
            vote.setCreateDate(new Date());
            vote.setCreateUser(userService.getAuthenticatedUser());
        } else {
            vote.setUpdateDate(new Date());
        }
        return voteRepository.save(vote);
    }

    public Vote getVoteByVotingAndUser(Voting voting) {
        return voteRepository.findVoteByVotingAndUser(voting, sessionInfoBean.getCurrentUser());
    }

}

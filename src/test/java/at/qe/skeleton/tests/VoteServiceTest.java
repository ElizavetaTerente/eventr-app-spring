package at.qe.skeleton.tests;

import at.qe.skeleton.model.Vote;
import at.qe.skeleton.model.VoteLocation;
import at.qe.skeleton.model.VoteTimeslot;
import at.qe.skeleton.model.Voting;
import at.qe.skeleton.services.UserService;
import at.qe.skeleton.services.VoteService;
import at.qe.skeleton.services.VotingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@SpringBootTest
@WebAppConfiguration

public class VoteServiceTest {

    @Autowired
    VoteService voteService;

    @Autowired
    UserService userService;

    @Autowired
    VotingService votingService;

    @Test
    @WithMockUser(username = "liza", authorities = { "ADMIN" })
    public void saveVoteTest() {

        Vote vote = new Vote();
        Date date = new Date(2022,01,3);
        vote.setUpdateDate(date);
        vote.setCreateDate(date);
        vote.setCreateUser(userService.loadUser("liza"));
        Long id = 123L;
        vote.setId(id);

        List vll = new ArrayList();
        VoteLocation voteLocation = new VoteLocation();
        vll.add(voteLocation);

        List vtll = new ArrayList();
        VoteTimeslot voteTimeslot = new VoteTimeslot();
        vll.add(voteLocation);

        vote.setVoteLocations(vll);
        vote.setVoteTimeslots(vtll);

        voteService.saveVote(vote);

        vote.setId(1234L);
        voteService.saveVote(vote);
    }

    @Test
    @WithMockUser(username = "liza", authorities = { "ADMIN" })
    public void getVoteByVotingAndUserTest() {

        String votingName = "Ablschlußessen";
        Voting voting = votingService.loadVoting(votingName);

        Assertions.assertNull(voteService.getVoteByVotingAndUser(voting));
    }


}

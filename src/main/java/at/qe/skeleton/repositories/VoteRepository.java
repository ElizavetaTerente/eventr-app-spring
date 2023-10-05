package at.qe.skeleton.repositories;

import at.qe.skeleton.model.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoteRepository extends AbstractRepository<Vote, Long> {
    Vote findVoteById(Long id);

    @Query("SELECT v FROM Vote v WHERE create_user_username = :user and voting_name = :voting")
    Vote findVoteByVotingAndUser(@Param("voting") Voting voting, @Param("user") User user);
}

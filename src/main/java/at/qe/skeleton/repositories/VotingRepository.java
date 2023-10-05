package at.qe.skeleton.repositories;

import at.qe.skeleton.model.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VotingRepository extends AbstractRepository<Voting, String> {

    Voting findVotingByName(String name);

    @Query("SELECT v FROM Voting v WHERE VOTING_ACTIVE = :active")
    List<Voting> findVotingsByStatus(@Param("active") Boolean active);

    @Query("SELECT e FROM User u JOIN u.votings e WHERE :user = u and voting_active = 'true'")
    List<Voting> findActiveVotingsByUser(@Param("user") User user);

    @Query("SELECT v FROM Voting v WHERE VOTING_ACTIVE = 'true' and CURRENT_TIMESTAMP > VOTING_STOP")
    List<Voting> findExpiredVotings();
}

package at.qe.skeleton.repositories;

import at.qe.skeleton.model.Event;
import at.qe.skeleton.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends AbstractRepository<Event, String> {
    Event findFirstByName(String name);

    @Query("SELECT e FROM User u JOIN u.events e WHERE :user = u and CURRENT_TIMESTAMP > e.start")
    List<Event> findHistoryByUser(@Param("user") User user);

    @Query("SELECT e FROM User u JOIN u.events e WHERE :user = u and CURRENT_TIMESTAMP < e.start")
    List<Event> findFutureEventsByUser(@Param("user")User user);
}
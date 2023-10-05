package at.qe.skeleton.repositories;

import at.qe.skeleton.model.Location;
import at.qe.skeleton.model.User;
import at.qe.skeleton.model.UserRole;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository for managing {@link User} entities.
 *
 * This class is part of the skeleton project provided for students of the
 * courses "Software Architecture" and "Software Engineering" offered by the
 * University of Innsbruck.
 */
public interface LocationRepository extends AbstractRepository<Location, String> {
    Location findLocationByName(String name);

    List<Location> findByActiveTrue();
}
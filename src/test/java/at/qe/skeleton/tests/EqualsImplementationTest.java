package at.qe.skeleton.tests;

import at.qe.skeleton.model.*;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

/**
 * Tests to ensure that each entity's implementation of equals conforms to the
 * contract. See {@linkplain http://www.jqno.nl/equalsverifier/} for more
 * information.
 *
 * This class is part of the skeleton project provided for students of the
 * courses "Software Architecture" and "Software Engineering" offered by the
 * University of Innsbruck.
 */
public class EqualsImplementationTest {

    @Test
    public void testModelsEqualsContract() {
        User user1 = new User();
        user1.setUsername("user1");
        User user2 = new User();
        user2.setUsername("user2");

        Object ArrayList;
        EqualsVerifier.forPackage("at.qe.skeleton.model")
                .except(Voting.class, Event.class,Location.class,Tag.class,User.class, Vote.class,VoteLocation.class)
                .withPrefabValues(User.class, user1, user2)
                .suppress(Warning.STRICT_INHERITANCE, Warning.ALL_FIELDS_SHOULD_BE_USED,
                        Warning.INHERITED_DIRECTLY_FROM_OBJECT)
                .verify();
    }

}
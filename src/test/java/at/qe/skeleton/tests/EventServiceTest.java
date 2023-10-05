package at.qe.skeleton.tests;

import at.qe.skeleton.model.Event;
import at.qe.skeleton.model.Location;
import at.qe.skeleton.model.User;
import at.qe.skeleton.model.UserRole;
import at.qe.skeleton.services.EventService;
import at.qe.skeleton.services.LocationService;
import at.qe.skeleton.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;

@Transactional
@SpringBootTest
@WebAppConfiguration
public class EventServiceTest {

        @Autowired
        EventService eventService;

        @Autowired
        UserService userService;

        @Autowired
        LocationService locationService;

        @Test
        @WithMockUser(username = "user", authorities = { "USER" })
        public void datainitializationTest() {
                Assertions.assertEquals(7, eventService.getAllEvents().size(),
                                "Insufficient amount of events initialized for test data source");
                for (Event event : eventService.getAllEvents()) {
                        if ("Essen ArbeitsGruppe Software-Architecktur".equals(event.getName())) {
                                Assertions.assertEquals(4, event.getParticipants().size(),
                                                "Event \"" + event + "\" does not have right number of participants");
                                Assertions.assertNotNull(event.getCreateUser(),
                                                "Event \"" + event + "\" does not have a createUser defined");
                                Assertions.assertNotNull(event.getCreateDate(),
                                                "Event \"" + event + "\" does not have a createDate defined");
                                Assertions.assertNull(event.getUpdateUser(),
                                                "Event \"" + event + "\" has a updateUser defined");
                                Assertions.assertNull(event.getUpdateDate(),
                                                "Event \"" + event + "\" has a updateDate defined");
                                Assertions.assertEquals(4, event.getNumParticipants());

                        } else if ("Dinner fo One".equals(event.getName())) {
                                Assertions.assertEquals(1, event.getParticipants().size(),
                                                "Event \"" + event + "\" does not have right number of participants");
                                Assertions.assertNotNull(event.getCreateUser(),
                                                "Event \"" + event + "\" does not have a createUser defined");
                                Assertions.assertNotNull(event.getCreateDate(),
                                                "Event \"" + event + "\" does not have a createDate defined");
                                Assertions.assertNull(event.getUpdateUser(),
                                                "Event \"" + event + "\" has a updateUser defined");
                                Assertions.assertNull(event.getUpdateDate(),
                                                "Event \"" + event + "\" has a updateDate defined");
                        } else if ("Morgensemmel 12".equals(event.getName())) {
                                Assertions.assertEquals(2, event.getParticipants().size(),
                                                "Event \"" + event + "\" does not have right number of participants");
                                Assertions.assertNotNull(event.getCreateUser(),
                                                "Event \"" + event + "\" does not have a createUser defined");
                                Assertions.assertNotNull(event.getCreateDate(),
                                                "Event \"" + event + "\" does not have a createDate defined");
                                Assertions.assertNull(event.getUpdateUser(),
                                                "Event \"" + event + "\" has a updateUser defined");
                                Assertions.assertNull(event.getUpdateDate(),
                                                "Event \"" + event + "\" has a updateDate defined");
                        } else if ("Test4".equals(event.getName())) {
                                Assertions.assertEquals(2, event.getParticipants().size(),
                                                "Event \"" + event + "\" does not have right number of participants");
                                Assertions.assertNotNull(event.getCreateUser(),
                                                "Event \"" + event + "\" does not have a createUser defined");
                                Assertions.assertNotNull(event.getCreateDate(),
                                                "Event \"" + event + "\" does not have a createDate defined");
                                Assertions.assertNull(event.getUpdateUser(),
                                                "Event \"" + event + "\" has a updateUser defined");
                                Assertions.assertNull(event.getUpdateDate(),
                                                "Event \"" + event + "\" has a updateDate defined");
                        } else if ("pizzatime".equals(event.getName())) {
                                Assertions.assertEquals(4, event.getParticipants().size(),
                                                "Event \"" + event + "\" does not have right number of participants");
                                Assertions.assertNotNull(event.getCreateUser(),
                                                "Event \"" + event + "\" does not have a createUser defined");
                                Assertions.assertNotNull(event.getCreateDate(),
                                                "Event \"" + event + "\" does not have a createDate defined");
                                Assertions.assertNull(event.getUpdateUser(),
                                                "Event \"" + event + "\" has a updateUser defined");
                                Assertions.assertNull(event.getUpdateDate(),
                                                "Event \"" + event + "\" has a updateDate defined");
                        } else if ("CARNEval".equals(event.getName())) {
                                Assertions.assertEquals(3, event.getParticipants().size(),
                                                "Event \"" + event + "\" does not have right number of participants");
                                Assertions.assertNotNull(event.getCreateUser(),
                                                "Event \"" + event + "\" does not have a createUser defined");
                                Assertions.assertNotNull(event.getCreateDate(),
                                                "Event \"" + event + "\" does not have a createDate defined");
                                Assertions.assertNull(event.getUpdateUser(),
                                                "Event \"" + event + "\" has a updateUser defined");
                                Assertions.assertNull(event.getUpdateDate(),
                                                "Event \"" + event + "\" has a updateDate defined");
                        } else if ("Morgensemmel 13".equals(event.getName())) {
                                Assertions.assertEquals(3, event.getParticipants().size(),
                                                "Event \"" + event + "\" does not have right number of participants");
                                Assertions.assertNotNull(event.getCreateUser(),
                                                "Event \"" + event + "\" does not have a createUser defined");
                                Assertions.assertNotNull(event.getCreateDate(),
                                                "Event \"" + event + "\" does not have a createDate defined");
                                Assertions.assertNull(event.getUpdateUser(),
                                                "Event \"" + event + "\" has a updateUser defined");
                                Assertions.assertNull(event.getUpdateDate(),
                                                "Event \"" + event + "\" has a updateDate defined");
                        } else {
                                Assertions.fail("Unknown event \"" + event.getName()
                                                + "\" loaded from test data source via EventService.getAllEvents");
                        }
                }

        }

        @Test
        @WithMockUser(username = "user", authorities = { "USER" })
        public void loadEventTest() {
                Event loadedEvent = eventService.loadEvent("Test4");
                Assertions.assertNotNull(loadedEvent);
        }

        @Test
        @WithMockUser(username = "user", authorities = { "USER" })
        public void saveEventTest() {
                String eventName = "Test4";
                Event loadedEvent = eventService.loadEvent(eventName);
                Assertions.assertNotNull(loadedEvent);
                Assertions.assertEquals(eventName, loadedEvent.getName(), "Call to loadEvent returned wrong event");
                loadedEvent.setName("Test5");
                eventService.saveEvent(loadedEvent);
        }

        @Test
        @WithMockUser(username = "admin", authorities = { "ADMIN" })
        public void CreateAndSafeEventTest() {

                String eventname = "testEvent";
                LocalDateTime start = LocalDateTime.of(2022, 02, 01, 18, 00);
                LocalDateTime end = LocalDateTime.of(2022, 02, 01, 20, 00);
                LocalDateTime voting_start = LocalDateTime.of(2022, 01, 31, 9, 00);
                LocalDateTime voting_end = LocalDateTime.of(2022, 01, 31, 21, 00);
                Date date = new Date(2022, 01, 3);
                Event toBeCreatedEvent = new Event();
                toBeCreatedEvent.setName(eventname);
                toBeCreatedEvent.setStart(start);
                toBeCreatedEvent.setEnd(end);
                toBeCreatedEvent.setVoting_start(voting_start);
                toBeCreatedEvent.setVoting_end(voting_end);
                toBeCreatedEvent.setUpdateDate(date);
                toBeCreatedEvent.setId(eventname);
                toBeCreatedEvent.setNumParticipants(3);

                String locName = "Mensa Technik";
                Location loadedLocation = locationService.loadLocation(locName);

                toBeCreatedEvent.setLocation(loadedLocation);

                eventService.saveEvent(toBeCreatedEvent);
                User loadedUser = userService.loadUser("test_user");
                toBeCreatedEvent.setUpdateUser(loadedUser);

                List pl = new ArrayList<>();
                pl.add(loadedUser);
                toBeCreatedEvent.setParticipants(pl);

                Event freshlyCreatedEvent = eventService.loadEvent(eventname);
                Assertions.assertNotNull(freshlyCreatedEvent,
                                "New event could not be loaded from test data source after being saved");
                Assertions.assertEquals(eventname, freshlyCreatedEvent.getName(),
                                "New event could not be loaded from test data source after being saved");
                Assertions.assertNotNull(freshlyCreatedEvent.getCreateDate(),
                                "Event \"" + eventname + "\" does not have a createDate defined after being saved");
                Assertions.assertEquals(start, freshlyCreatedEvent.getStart());
                Assertions.assertEquals(end, freshlyCreatedEvent.getEnd());
                Assertions.assertEquals(voting_start, freshlyCreatedEvent.getVoting_start());
                Assertions.assertEquals(voting_end, freshlyCreatedEvent.getVoting_end());
                Assertions.assertEquals(locName, freshlyCreatedEvent.getLocation().getName());
                Assertions.assertEquals(1, toBeCreatedEvent.getNumParticipants());

                Assertions.assertEquals(8, eventService.getAllEvents().size(),
                                "Insufficient amount of events initialized for test data source");

        }

        @Test
        @WithMockUser(username = "user", authorities = { "ADMIN" })
        public void getFutureEventsTest() {

                User test_user = userService.loadUser("liza");
                Assertions.assertEquals(2, eventService.getFutureEvents(test_user).size(),
                                "Insufficient amount of events initialized for test data source");
        }

        @Test
        @WithMockUser(username = "user", authorities = { "ADMIN" })
        public void getUserHistoryTest() {

                User test_user = userService.loadUser("liza");
                Assertions.assertEquals(1, eventService.getUserHistory(test_user).size(),
                                "Insufficient amount of events initialized for test data source");
        }

        @Test
        @WithMockUser(username = "admin", authorities = { "Admin" })
        public void deleteEventAsAdminTest() {

                String eventname = "Test4";
                Event freshlyCreatedEvent = eventService.loadEvent(eventname);
                Assertions.assertNotNull(freshlyCreatedEvent,
                                "New event could not be loaded from test data source after being saved");
                Assertions.assertEquals(eventname, freshlyCreatedEvent.getName(),
                                "New event could not be loaded from test data source after being saved");
                Assertions.assertNotNull(freshlyCreatedEvent.getCreateDate(),
                                "Event \"" + eventname + "\" does not have a createDate defined after being saved");
                Assertions.assertNotNull(freshlyCreatedEvent.getCreateUser(),
                                "Event \"" + eventname + "\" does not have a createUser defined after being saved");

                eventService.deleteEvent(freshlyCreatedEvent);

                Assertions.assertEquals(6, eventService.getAllEvents().size(),
                                "No event has been deleted after calling UserService.deleteUser");
                Event deletedEvent = eventService.loadEvent(eventname);
                Assertions.assertNull(deletedEvent, "Deleted Event \"" + eventname
                                + "\" could still be loaded from test data source via EventService.loadEvent");

                for (Event remainingEvent : eventService.getAllEvents()) {
                        Assertions.assertNotEquals(freshlyCreatedEvent.getName(), remainingEvent.getName(),
                                        "Deleted Event \""
                                                        + eventname
                                                        + "\" could still be loaded from test data source via EventService.getAllEvents");
                }

        }

        @Test
        @WithMockUser(username = "manager", authorities = { "MANAGER" })
        public void deleteEventAsManagerTest() {

                String eventname = "Test4";
                Event freshlyCreatedEvent = eventService.loadEvent(eventname);
                Assertions.assertNotNull(freshlyCreatedEvent,
                                "New event could not be loaded from test data source after being saved");
                Assertions.assertEquals(eventname, freshlyCreatedEvent.getName(),
                                "New event could not be loaded from test data source after being saved");
                Assertions.assertNotNull(freshlyCreatedEvent.getCreateDate(),
                                "Event \"" + eventname + "\" does not have a createDate defined after being saved");
                Assertions.assertNotNull(freshlyCreatedEvent.getCreateUser(),
                                "Event \"" + eventname + "\" does not have a createUser defined after being saved");

                eventService.deleteEvent(freshlyCreatedEvent);

                Assertions.assertEquals(7, eventService.getAllEvents().size(),
                                "A event has been deleted after calling UserService.deleteUser");
                Event deletedEvent = eventService.loadEvent(eventname);
                Assertions.assertNotNull(deletedEvent, "Deleted Event \"" + eventname
                                + "\" could not be loaded from test data source via EventService.loadEvent");

        }

        @Test
        @WithMockUser(username = "test_user", authorities = { "USER" })
        public void deleteEventAsUserTest() {

                String eventname = "Test4";
                Event freshlyCreatedEvent = eventService.loadEvent(eventname);
                Assertions.assertNotNull(freshlyCreatedEvent,
                                "New event could not be loaded from test data source after being saved");
                Assertions.assertEquals(eventname, freshlyCreatedEvent.getName(),
                                "New event could not be loaded from test data source after being saved");
                Assertions.assertNotNull(freshlyCreatedEvent.getCreateDate(),
                                "Event \"" + eventname + "\" does not have a createDate defined after being saved");
                Assertions.assertNotNull(freshlyCreatedEvent.getCreateUser(),
                                "Event \"" + eventname + "\" does not have a createUser defined after being saved");

                eventService.deleteEvent(freshlyCreatedEvent);

                Assertions.assertEquals(7, eventService.getAllEvents().size(),
                                "A event has been deleted after calling UserService.deleteUser");
                Event deletedEvent = eventService.loadEvent(eventname);
                Assertions.assertNotNull(deletedEvent, "Deleted Event \"" + eventname
                                + "\" could not be loaded from test data source via EventService.loadEvent");

        }

        @Test
        @WithMockUser(username = "test_user", authorities = { "USER" })
        public void deleteEventAsCurrentUserTest() {

                String eventname = "testEvent";

                LocalDateTime start = LocalDateTime.of(2022, 02, 01, 18, 00);
                LocalDateTime end = LocalDateTime.of(2022, 02, 01, 20, 00);
                LocalDateTime voting_start = LocalDateTime.of(2022, 01, 31, 9, 00);
                LocalDateTime voting_end = LocalDateTime.of(2022, 01, 31, 21, 00);

                Event toBeCreatedEvent = new Event();
                toBeCreatedEvent.setName(eventname);
                toBeCreatedEvent.setStart(start);
                toBeCreatedEvent.setEnd(end);
                toBeCreatedEvent.setVoting_start(voting_start);
                toBeCreatedEvent.setVoting_end(voting_end);

                eventService.saveEvent(toBeCreatedEvent);
                eventService.deleteEvent(toBeCreatedEvent);

                Assertions.assertEquals(7, eventService.getAllEvents().size(),
                                "No event has been deleted after calling UserService.deleteUser");
                Event deletedEvent = eventService.loadEvent(eventname);
                Assertions.assertNull(deletedEvent, "Deleted Event \"" + eventname
                                + "\" could still be loaded from test data source via EventService.loadEvent");

                for (Event remainingEvent : eventService.getAllEvents()) {
                        Assertions.assertNotEquals(toBeCreatedEvent.getName(), remainingEvent.getName(),
                                        "Deleted Event \""
                                                        + eventname
                                                        + "\" could still be loaded from test data source via EventService.getAllEvents");
                }

        }

        @Test
        @WithMockUser(username = "test_user", authorities = { "USER" })
        public void CreateEventTest() {

                String eventname = "testEvent";

                LocalDateTime start = LocalDateTime.of(2022, 02, 01, 18, 00);
                LocalDateTime end = LocalDateTime.of(2022, 02, 01, 20, 00);
                LocalDateTime voting_start = LocalDateTime.of(2022, 01, 31, 9, 00);
                LocalDateTime voting_end = LocalDateTime.of(2022, 01, 31, 21, 00);

                Event toBeCreatedEvent = new Event();
                toBeCreatedEvent.setName(eventname);
                toBeCreatedEvent.setStart(start);
                toBeCreatedEvent.setEnd(end);
                toBeCreatedEvent.setVoting_start(voting_start);
                toBeCreatedEvent.setVoting_end(voting_end);

                eventService.createEvent(toBeCreatedEvent);
                Assertions.assertNotNull(eventService.loadEvent(eventname));

        }

}

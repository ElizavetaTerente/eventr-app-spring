package at.qe.skeleton.tests;

import at.qe.skeleton.model.*;
import at.qe.skeleton.services.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@SpringBootTest
@WebAppConfiguration
public class LocationServiceTest {

        @Autowired
        EventService eventService;

        @Autowired
        VotingService votingService;

        @Autowired
        UserService userService;

        @Autowired
        LocationService locationService;

        @Autowired
        TagService tagService;

        @Test
        @WithMockUser(username = "user", authorities = { "USER" })
        public void datainitializationTest() {
                Assertions.assertEquals(6, locationService.getAllLocations().size(),
                                "Insufficient amount of locations initialized for test data source");
                for (Location location : locationService.getAllLocations()) {
                        if ("9b".equals(location.getName())) {
                                Assertions.assertEquals(6, locationService.getAllLocations().size(),
                                                "Insufficient amount of locations initialized for test data source");
                                Assertions.assertNotNull(location.getCreateUser(),
                                                "Location \"" + location + "\" does not have a createUser defined");
                                Assertions.assertNotNull(location.getCreateDate(),
                                                "Location \"" + location + "\" does not have a createDate defined");
                                Assertions.assertNull(location.getUpdateUser(),
                                                "Location \"" + location + "\" has a updateUser defined");
                                Assertions.assertNull(location.getUpdateDate(),
                                                "Location \"" + location + "\" has a updateDate defined");
                                Assertions.assertEquals(2, location.getTags().size(),
                                                "Insufficient amount of tags of location initialized for test data source");
                                Assertions.assertEquals(0, location.getVotings().size(),
                                                "Insufficient amount of tags of location initialized for test data source");

                        } else if ("Mensa Technik".equals(location.getName())) {
                                Assertions.assertNotNull(location.getCreateUser(),
                                                "Location \"" + location + "\" does not have a createUser defined");
                                Assertions.assertNotNull(location.getCreateDate(),
                                                "Location \"" + location + "\" does not have a createDate defined");
                                Assertions.assertNull(location.getUpdateUser(),
                                                "Location \"" + location + "\" has a updateUser defined");
                                Assertions.assertNull(location.getUpdateDate(),
                                                "Location \"" + location + "\" has a updateDate defined");
                                Assertions.assertEquals(1, location.getTags().size(),
                                                "Insufficient amount of tags of location initialized for test data source");

                        } else if ("Ruetz".equals(location.getName())) {
                                Assertions.assertNotNull(location.getCreateUser(),
                                                "Location \"" + location + "\" does not have a createUser defined");
                                Assertions.assertNotNull(location.getCreateDate(),
                                                "Location \"" + location + "\" does not have a createDate defined");
                                Assertions.assertNull(location.getUpdateUser(),
                                                "Location \"" + location + "\" has a updateUser defined");
                                Assertions.assertNull(location.getUpdateDate(),
                                                "Location \"" + location + "\" has a updateDate defined");
                                Assertions.assertEquals(1, location.getTags().size(),
                                                "Insufficient amount of tags of location initialized for test data source");

                        } else if ("Bamboo".equals(location.getName())) {
                                Assertions.assertNotNull(location.getCreateUser(),
                                                "Location \"" + location + "\" does not have a createUser defined");
                                Assertions.assertNotNull(location.getCreateDate(),
                                                "Location \"" + location + "\" does not have a createDate defined");
                                Assertions.assertNull(location.getUpdateUser(),
                                                "Location \"" + location + "\" has a updateUser defined");
                                Assertions.assertNull(location.getUpdateDate(),
                                                "Location \"" + location + "\" has a updateDate defined");
                                Assertions.assertEquals(2, location.getTags().size(),
                                                "Insufficient amount of tags of location initialized for test data source");

                        } else if ("UnaPizza".equals(location.getName())) {
                                Assertions.assertNotNull(location.getCreateUser(),
                                                "Location \"" + location + "\" does not have a createUser defined");
                                Assertions.assertNotNull(location.getCreateDate(),
                                                "Location \"" + location + "\" does not have a createDate defined");
                                Assertions.assertNull(location.getUpdateUser(),
                                                "Location \"" + location + "\" has a updateUser defined");
                                Assertions.assertNull(location.getUpdateDate(),
                                                "Location \"" + location + "\" has a updateDate defined");
                                Assertions.assertEquals(1, location.getTags().size(),
                                                "Insufficient amount of tags of location initialized for test data source");

                        } else if ("Machete".equals(location.getName())) {
                                Assertions.assertNotNull(location.getCreateUser(),
                                                "Location \"" + location + "\" does not have a createUser defined");
                                Assertions.assertNotNull(location.getCreateDate(),
                                                "Location \"" + location + "\" does not have a createDate defined");
                                Assertions.assertNull(location.getUpdateUser(),
                                                "Location \"" + location + "\" has a updateUser defined");
                                Assertions.assertNull(location.getUpdateDate(),
                                                "Location \"" + location + "\" has a updateDate defined");
                                Assertions.assertEquals(2, location.getTags().size(),
                                                "Insufficient amount of tags of location initialized for test data source");

                        } else {
                                Assertions.fail("Unknown event \"" + location.getName()
                                                + "\" loaded from test data source");
                        }
                }

        }

        @Test
        @WithMockUser(username = "user", authorities = { "USER" })
        public void deleteLocationAsUserTest() {
                Assertions.assertThrows(org.springframework.security.access.AccessDeniedException.class, () -> {
                        String name = "9b";
                        Location toBeDeletedLocation = locationService.loadLocation(name);
                        Assertions.assertNotNull(toBeDeletedLocation,
                                        "Location could not be loaded from test data source");
                        locationService.deleteLocation(toBeDeletedLocation);
                });
        }

        @DirtiesContext
        @Test
        @WithMockUser(username = "admin", authorities = { "ADMIN" })
        public void saveLocationAsAdminTest() {
                Location newLocation = new Location();
                newLocation.setName("test");

                locationService.saveLocation(newLocation);

        }

        @DirtiesContext
        @Test
        @WithMockUser(username = "manager", authorities = { "MANAGER" })
        public void saveLocationAsManagerTest() {
                Location newLocation = new Location();
                newLocation.setName("test");

                locationService.saveLocation(newLocation);

        }

        @DirtiesContext
        @Test
        @WithMockUser(username = "user", authorities = { "USER" })
        public void saveLocationAsUserTest() {
                Assertions.assertThrows(org.springframework.security.access.AccessDeniedException.class, () -> {

                        Location newLocation = new Location();
                        newLocation.setName("test");

                        locationService.saveLocation(newLocation);
                });

        }

        @Test
        @WithMockUser(username = "admin", authorities = { "ADMIN" })
        public void CreateAndSafeLocationTest() {

                OpeningHour openingHour = new OpeningHour();
                openingHour.setOpened(true);
                openingHour.setWeekday(Weekday.MO);
                LocalTime open = LocalTime.of(9, 00);
                LocalTime close = LocalTime.of(21, 00);
                openingHour.setOpen(open);
                openingHour.setClose(close);

                Assertions.assertTrue(openingHour.getOpened());
                Assertions.assertEquals(Weekday.MO, openingHour.getWeekday());
                Assertions.assertEquals(open, openingHour.getOpen());
                Assertions.assertEquals(close, openingHour.getClose());

                String locName = "testLocation";
                Date date = new Date(2022, 01, 3);
                Location toBeCreatedLocation = new Location();
                toBeCreatedLocation.setId(locName);
                toBeCreatedLocation.setDescription("test location");
                toBeCreatedLocation.setUrl("http...");
                toBeCreatedLocation.setStreet("Hoettinger Au");
                toBeCreatedLocation.setCity("Ibk");
                toBeCreatedLocation.setUpdateDate(date);
                toBeCreatedLocation.setUpdateUser(userService.loadUser("test_user"));

                List vl = new ArrayList<>();
                vl.add(votingService.loadVoting("Ablschlußessen"));
                toBeCreatedLocation.setVotings(vl);

                List tl = new ArrayList<>();
                tl.add(tagService.loadTag("Alkohol"));
                toBeCreatedLocation.setTags(tl);

                List ohl = new ArrayList();
                ohl.add(openingHour);
                toBeCreatedLocation.setOpeningHours(ohl);

                locationService.saveLocation(toBeCreatedLocation);

                Location freshlyCreatedLocation = locationService.loadLocation(locName);
                Assertions.assertEquals("test location", freshlyCreatedLocation.getDescription());
                Assertions.assertEquals("http...", freshlyCreatedLocation.getUrl());
                Assertions.assertEquals("Hoettinger Au", freshlyCreatedLocation.getStreet());
                Assertions.assertEquals("Ibk", freshlyCreatedLocation.getCity());
                Assertions.assertNotNull(freshlyCreatedLocation.getOpeningHours());

                Assertions.assertEquals(7, eventService.getAllEvents().size(),
                                "Insufficient amount of events initialized for test data source");

        }

}

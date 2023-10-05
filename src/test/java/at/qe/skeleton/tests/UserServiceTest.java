package at.qe.skeleton.tests;

import at.qe.skeleton.services.EventService;
import at.qe.skeleton.services.VotingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.web.WebAppConfiguration;

import at.qe.skeleton.model.User;
import at.qe.skeleton.model.UserRole;
import at.qe.skeleton.services.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Some very basic tests for {@link UserService}.
 *
 * This class is part of the skeleton project provided for students of the
 * courses "Software Architecture" and "Software Engineering" offered by the
 * University of Innsbruck.
 */
@SpringBootTest
@WebAppConfiguration
public class UserServiceTest {

        @Autowired
        UserService userService;

        @Autowired
        VotingService votingService;

        @Autowired
        PasswordEncoder passwordEncoder;

        @Autowired
        EventService eventService;

        @Test
        @WithMockUser(username = "admin", authorities = { "ADMIN" })
        public void testDatainitialization() {
                for (User user : userService.getAllUsers()) {
                        if ("andi".equals(user.getUsername())) {
                                Assertions.assertTrue(user.getRoles().contains(UserRole.USER),
                                                "User \"" + user + "\" does not have role MANAGER");
                                Assertions.assertNotNull(user.getCreateUser(),
                                                "User \"" + user + "\" does not have a createUser defined");
                                Assertions.assertNotNull(user.getCreateDate(),
                                                "User \"" + user + "\" does not have a createDate defined");
                                Assertions.assertNull(user.getUpdateUser(),
                                                "User \"" + user + "\" has a updateUser defined");
                                Assertions.assertNull(user.getUpdateDate(),
                                                "User \"" + user + "\" has a updateDate defined");
                                Assertions.assertTrue(user.isEnabled(),
                                                "User \"" + user + "\" has is a right Enable status");

                        } else if ("plank".equals(user.getUsername())) {
                                Assertions.assertTrue(user.getRoles().contains(UserRole.USER),
                                                "User \"" + user + "\" does not have role MANAGER");
                                Assertions.assertNotNull(user.getCreateUser(),
                                                "User \"" + user + "\" does not have a createUser defined");
                                Assertions.assertNotNull(user.getCreateDate(),
                                                "User \"" + user + "\" does not have a createDate defined");
                                Assertions.assertNull(user.getUpdateUser(),
                                                "User \"" + user + "\" has a updateUser defined");
                                Assertions.assertNull(user.getUpdateDate(),
                                                "User \"" + user + "\" has a updateDate defined");
                                Assertions.assertTrue(user.isEnabled(),
                                                "User \"" + user + "\" has is a right Enable status");
                        } else if ("system".equals(user.getUsername())) {
                                Assertions.assertTrue(user.getRoles().contains(UserRole.ADMIN),
                                                "User \"" + user + "\" does not have role MANAGER");
                                Assertions.assertNotNull(user.getCreateUser(),
                                                "User \"" + user + "\" does not have a createUser defined");
                                Assertions.assertNotNull(user.getCreateDate(),
                                                "User \"" + user + "\" does not have a createDate defined");
                                Assertions.assertNull(user.getUpdateUser(),
                                                "User \"" + user + "\" has a updateUser defined");
                                Assertions.assertNull(user.getUpdateDate(),
                                                "User \"" + user + "\" has a updateDate defined");
                                Assertions.assertTrue(user.isEnabled(),
                                                "User \"" + user + "\" has is a right Enable status");
                        } else if ("liza".equals(user.getUsername())) {
                                Assertions.assertTrue(user.getRoles().contains(UserRole.USER),
                                                "User \"" + user + "\" does not have role MANAGER");
                                Assertions.assertNotNull(user.getCreateUser(),
                                                "User \"" + user + "\" does not have a createUser defined");
                                Assertions.assertNotNull(user.getCreateDate(),
                                                "User \"" + user + "\" does not have a createDate defined");
                                Assertions.assertNull(user.getUpdateUser(),
                                                "User \"" + user + "\" has a updateUser defined");
                                Assertions.assertNull(user.getUpdateDate(),
                                                "User \"" + user + "\" has a updateDate defined");
                                Assertions.assertTrue(user.isEnabled(),
                                                "User \"" + user + "\" has is a right Enable status");
                        } else if ("test_user".equals(user.getUsername())) {
                                Assertions.assertTrue(user.getRoles().contains(UserRole.USER),
                                                "User \"" + user + "\" does not have role ADMIN");
                                Assertions.assertNotNull(user.getCreateUser(),
                                                "User \"" + user + "\" does not have a createUser defined");
                                Assertions.assertNotNull(user.getCreateDate(),
                                                "User \"" + user + "\" does not have a createDate defined");
                                Assertions.assertNull(user.getUpdateUser(),
                                                "User \"" + user + "\" has a updateUser defined");
                                Assertions.assertNull(user.getUpdateDate(),
                                                "User \"" + user + "\" has a updateDate defined");
                                Assertions.assertTrue(user.isEnabled(),
                                                "User \"" + user + "\" has is a right Enable status");
                        } else if ("admin".equals(user.getUsername())) {
                                Assertions.assertTrue(user.getRoles().contains(UserRole.ADMIN),
                                                "User \"" + user + "\" does not have role ADMIN");
                                Assertions.assertTrue(user.getRoles().contains(UserRole.USER),
                                                "User \"" + user + "\" does not have role USER");
                                Assertions.assertNotNull(user.getCreateUser(),
                                                "User \"" + user + "\" does not have a createUser defined");
                                Assertions.assertNotNull(user.getCreateDate(),
                                                "User \"" + user + "\" does not have a createDate defined");
                                Assertions.assertNull(user.getUpdateUser(),
                                                "User \"" + user + "\" has a updateUser defined");
                                Assertions.assertNull(user.getUpdateDate(),
                                                "User \"" + user + "\" has a updateDate defined");
                                Assertions.assertTrue(user.isEnabled(),
                                                "User \"" + user + "\" has is a right Enable status");
                        } else if ("manager".equals(user.getUsername())) {
                                Assertions.assertTrue(user.getRoles().contains(UserRole.MANAGER),
                                                "User \"" + user + "\" does not have role MANAGER");
                                Assertions.assertTrue(user.getRoles().contains(UserRole.USER),
                                                "User \"" + user + "\" does not have role USER");
                                Assertions.assertNotNull(user.getCreateUser(),
                                                "User \"" + user + "\" does not have a createUser defined");
                                Assertions.assertNotNull(user.getCreateDate(),
                                                "User \"" + user + "\" does not have a createDate defined");
                                Assertions.assertNull(user.getUpdateUser(),
                                                "User \"" + user + "\" has a updateUser defined");
                                Assertions.assertNull(user.getUpdateDate(),
                                                "User \"" + user + "\" has a updateDate defined");
                                Assertions.assertTrue(user.isEnabled(),
                                                "User \"" + user + "\" has is a right Enable status");
                        } else if ("user".equals(user.getUsername())) {
                                Assertions.assertTrue(user.getRoles().contains(UserRole.USER),
                                                "User \"" + user + "\" does not have role USER");
                                Assertions.assertNotNull(user.getCreateUser(),
                                                "User \"" + user + "\" does not have a createUser defined");
                                Assertions.assertNotNull(user.getCreateDate(),
                                                "User \"" + user + "\" does not have a createDate defined");
                                Assertions.assertNull(user.getUpdateUser(),
                                                "User \"" + user + "\" has a updateUser defined");
                                Assertions.assertNull(user.getUpdateDate(),
                                                "User \"" + user + "\" has a updateDate defined");
                                Assertions.assertTrue(user.isEnabled(),
                                                "User \"" + user + "\" has is a right Enable status");
                        } else if ("alexandra".equals(user.getUsername())) {
                                Assertions.assertTrue(user.getRoles().contains(UserRole.USER),
                                                "User \"" + user + "\" does not have role USER");
                                Assertions.assertNotNull(user.getCreateUser(),
                                                "User \"" + user + "\" does not have a createUser defined");
                                Assertions.assertNotNull(user.getCreateDate(),
                                                "User \"" + user + "\" does not have a createDate defined");
                                Assertions.assertNull(user.getUpdateUser(),
                                                "User \"" + user + "\" has a updateUser defined");
                                Assertions.assertNull(user.getUpdateDate(),
                                                "User \"" + user + "\" has a updateDate defined");
                                Assertions.assertTrue(user.isEnabled(),
                                                "User \"" + user + "\" has is a right Enable status");
                        }
                }
        }

        @DirtiesContext
        @Test
        @WithMockUser(username = "admin", authorities = { "ADMIN" })
        public void testUpdateUser() {
                String username = "liza";
                User toBeSavedUser = userService.loadUser(username);
                Assertions.assertNotNull(toBeSavedUser,
                                "User \"" + username + "\" could not be loaded from test data source");

                Assertions.assertNull(toBeSavedUser.getUpdateUser(),
                                "User \"" + username + "\" has a updateUser defined");
                Assertions.assertNull(toBeSavedUser.getUpdateDate(),
                                "User \"" + username + "\" has a updateDate defined");

                toBeSavedUser.setEmail("changedEmail@gmail.com");
                toBeSavedUser.setPhone("+79643926494");
                userService.saveUser(toBeSavedUser);

                User freshlyLoadedUser = userService.loadUser("liza");
                Assertions.assertNotNull(freshlyLoadedUser,
                                "User \"" + username
                                                + "\" could not be loaded from test data source after being saved");
                freshlyLoadedUser.setUpdateUser(toBeSavedUser);
                Assertions.assertNotNull(freshlyLoadedUser.getUpdateUser(),
                                "User \"" + username + "\" does not have a updateUser defined after being saved");
                Assertions.assertNotNull(freshlyLoadedUser.getUpdateDate(),
                                "User \"" + username + "\" does not have a updateDate defined after being saved");
                Assertions.assertEquals("changedEmail@gmail.com", freshlyLoadedUser.getEmail(),
                                "User \"" + username
                                                + "\" does not have a the correct email attribute stored being saved");
                Assertions.assertEquals("+79643926494", freshlyLoadedUser.getPhone(),
                                "User \"" + username
                                                + "\" does not have a the correct mobile Phone attribute stored being saved");

        }

        @DirtiesContext
        @Test
        @WithMockUser(username = "user", authorities = { "USER" })
        public void testCreateUserAsUser() {
                Assertions.assertThrows(org.springframework.security.access.AccessDeniedException.class, () -> {

                        String username = "newuser";
                        String password = "passwd";
                        String fName = "New";
                        String lName = "User";
                        String email = "new-email@whatever.wherever";
                        User toBeCreatedUser = new User();
                        toBeCreatedUser.setId(username);
                        toBeCreatedUser.setPassword(password);
                        toBeCreatedUser.setEnabled(true);
                        toBeCreatedUser.setFirstName(fName);
                        toBeCreatedUser.setLastName(lName);
                        toBeCreatedUser.setEmail(email);
                        toBeCreatedUser.setRoles(Sets.newSet(UserRole.USER, UserRole.MANAGER));

                        List vl = new ArrayList<>();
                        vl.add(votingService.loadVoting("Ablschlußessen"));
                        toBeCreatedUser.setVotings(vl);

                        List el = new ArrayList<>();
                        el.add(eventService.loadEvent("Test4"));
                        toBeCreatedUser.setEvents(el);

                        userService.saveUser(toBeCreatedUser);
                        Assertions.assertEquals(vl, toBeCreatedUser.getVotings());
                        Assertions.assertEquals(el, toBeCreatedUser.getEvents());
                });
        }

        @DirtiesContext
        @Test
        @WithMockUser(username = "manager", authorities = { "MANAGER" })
        public void testCreateUserAsManager() {
                Assertions.assertThrows(org.springframework.security.access.AccessDeniedException.class, () -> {
                        String username = "newuser";
                        String password = "passwd";
                        String fName = "New";
                        String lName = "User";
                        String email = "new-email@whatever.wherever";
                        User toBeCreatedUser = new User();
                        toBeCreatedUser.setUsername(username);
                        toBeCreatedUser.setPassword(password);
                        toBeCreatedUser.setEnabled(true);
                        toBeCreatedUser.setFirstName(fName);
                        toBeCreatedUser.setLastName(lName);
                        toBeCreatedUser.setEmail(email);
                        toBeCreatedUser.setRoles(Sets.newSet(UserRole.USER, UserRole.MANAGER));
                        userService.saveUser(toBeCreatedUser);
                });
        }

        @DirtiesContext
        @Test
        @WithMockUser(username = "admin", authorities = { "ADMIN" })
        public void testCreateUserAsAdmin() {

                String username = "newuser";
                String password = "passwd";
                String fName = "New";
                String lName = "User";
                String email = "new-email@whatever.wherever";
                User toBeCreatedUser = new User();
                toBeCreatedUser.setUsername(username);
                toBeCreatedUser.setPassword(password);
                toBeCreatedUser.setEnabled(true);
                toBeCreatedUser.setFirstName(fName);
                toBeCreatedUser.setLastName(lName);
                toBeCreatedUser.setEmail(email);
                toBeCreatedUser.setRoles(Sets.newSet(UserRole.USER, UserRole.MANAGER));
                userService.saveUser(toBeCreatedUser);

                User freshlyCreatedUser = userService.loadUser(username);
                Assertions.assertNotNull(freshlyCreatedUser,
                                "New user could not be loaded from test data source after being saved");
                Assertions.assertEquals(username, freshlyCreatedUser.getUsername(),
                                "New user could not be loaded from test data source after being saved");
                Assertions.assertEquals(fName, freshlyCreatedUser.getFirstName(),
                                "User \"" + username
                                                + "\" does not have a the correct firstName attribute stored being saved");
                Assertions.assertEquals(lName, freshlyCreatedUser.getLastName(),
                                "User \"" + username
                                                + "\" does not have a the correct lastName attribute stored being saved");
                Assertions.assertEquals(email, freshlyCreatedUser.getEmail(),
                                "User \"" + username
                                                + "\" does not have a the correct email attribute stored being saved");
                Assertions.assertTrue(freshlyCreatedUser.getRoles().contains(UserRole.MANAGER),
                                "User \"" + username + "\" does not have role MANAGER");
                Assertions.assertTrue(freshlyCreatedUser.getRoles().contains(UserRole.USER),
                                "User \"" + username + "\" does not have role EMPLOYEE");
                Assertions.assertNotNull(freshlyCreatedUser.getCreateDate(),
                                "User \"" + username + "\" does not have a createDate defined after being saved");
        }

        @Test
        @WithMockUser(username = "admin", authorities = { "ADMIN" })
        public void testExceptionForEmptyUsername() {
                Assertions.assertThrows(java.lang.IllegalArgumentException.class, () -> {
                        User adminUser = userService.loadUser("liza");
                        Assertions.assertNotNull(adminUser, "Admin user could not be loaded from test data source");

                        User toBeCreatedUser = new User();
                        userService.saveUser(toBeCreatedUser);
                });
        }

        @Test
        public void testUnauthenticateddLoadUsers() {
                Assertions.assertThrows(
                                org.springframework.security.authentication.AuthenticationCredentialsNotFoundException.class,
                                () -> {
                                        for (User user : userService.getAllUsers()) {
                                                Assertions.fail("Call to userService.getAllUsers should not work without proper authorization");
                                        }
                                });
        }

        @Test
        @WithMockUser(username = "liza", authorities = { "USER" })
        public void testLoadUserAsCurrentUser() {
                // user can load himself
                User user1 = userService.loadUser("liza");

                // and can not load other users
                Assertions.assertThrows(org.springframework.security.access.AccessDeniedException.class, () -> {
                        User user2 = userService.loadUser("andi");
                        Assertions.fail(
                                        "Call to userService.loadUser should not work without proper authorization for other users than the authenticated one");
                });

        }

        @Test
        @WithMockUser(username = "user", authorities = { "USER" })
        public void testLoadUserAsUser() {
                Assertions.assertThrows(org.springframework.security.access.AccessDeniedException.class, () -> {
                        User user = userService.loadUser("liza");
                        Assertions.fail(
                                        "Call to userService.loadUser should not work without proper authorization for other users than the authenticated one");
                });
        }

        @Test
        @WithMockUser(username = "manager", authorities = { "MANAGER" })
        public void testLoadUserAsManager() {
                String username = "liza";
                User user = userService.loadUser(username);
                Assertions.assertEquals(username, user.getUsername(),
                                "Call to userService.loadUser returned wrong user");
        }

        @Test
        @WithMockUser(username = "admin", authorities = { "ADMIN" })
        public void testLoadUserAsAdmin() {
                String username = "liza";
                User user = userService.loadUser(username);
                Assertions.assertEquals(username, user.getUsername(),
                                "Call to userService.loadUser returned wrong user");
        }

        @Test
        public void testUnauthenticateddSaveUsers() {
                Assertions.assertThrows(
                                org.springframework.security.authentication.AuthenticationCredentialsNotFoundException.class,
                                () -> {
                                        String username = "liza";
                                        User user = userService.loadUser(username);
                                        Assertions.assertEquals(username, user.getUsername(),
                                                        "Call to userService.loadUser returned wrong user");
                                        user.setPhone("+79643926494");
                                        userService.saveUser(user);
                                });
        }

        @Test
        @WithMockUser(username = "user1", authorities = { "USER" })
        public void testSaveUserAsUser() {
                Assertions.assertThrows(org.springframework.security.access.AccessDeniedException.class, () -> {
                        String username = "liza";
                        User user = userService.loadUser(username);
                        Assertions.assertEquals(username, user.getUsername(),
                                        "Call to userService.loadUser returned wrong user");
                        user.setPhone("+79643926494");
                        userService.saveUser(user);
                });
        }

        @Test
        @WithMockUser(username = "manager", authorities = { "MANAGER" })
        public void testSaveUserAsManager() {
                Assertions.assertThrows(org.springframework.security.access.AccessDeniedException.class, () -> {
                        String username = "liza";
                        User user = userService.loadUser(username);
                        Assertions.assertEquals(username, user.getUsername(),
                                        "Call to userService.loadUser returned wrong user");
                        user.setPhone("+79643926494");
                        userService.saveUser(user);
                });
        }

        @Test
        @WithMockUser(username = "admin", authorities = { "ADMIN" })
        public void testSaveUserAsAdmin() {
                String username = "liza";
                User user = userService.loadUser(username);
                Assertions.assertEquals(username, user.getUsername(),
                                "Call to userService.loadUser returned wrong user");
                user.setPhone("+79643926494");
                userService.saveUser(user);
        }

        @Test
        @WithMockUser(username = "user", authorities = { "USER" })
        public void testDeleteUserAsUser() {
                Assertions.assertThrows(org.springframework.security.access.AccessDeniedException.class, () -> {
                        User user = userService.loadUser("liza");
                        Assertions.assertEquals("liza", user.getUsername(),
                                        "Call to userService.loadUser returned wrong user");
                        userService.deleteUser(user);
                });
        }

        @Test
        @WithMockUser(username = "manager", authorities = { "MANAGER" })
        public void testDeleteUserAsManager() {
                Assertions.assertThrows(org.springframework.security.access.AccessDeniedException.class, () -> {
                        User user = userService.loadUser("liza");
                        Assertions.assertEquals("liza", user.getUsername(),
                                        "Call to userService.loadUser returned wrong user");
                        userService.deleteUser(user);
                });
        }

        @DirtiesContext
        @Test
        @WithMockUser(username = "admin", authorities = { "ADMIN" })
        public void testDeleteUserAsAdmin() {
                String username = "test_user";
                User toBeDeletedUser = userService.loadUser(username);
                Assertions.assertNotNull(toBeDeletedUser,
                                "User \"" + username + "\" could not be loaded from test data source");
                userService.deleteUser(toBeDeletedUser);

                Assertions.assertEquals(7, userService.getAllUsers().size(),
                                "No user has been deleted after calling UserService.deleteUser");
                User deletedUser = userService.loadUser(username);
                Assertions.assertNull(deletedUser, "Deleted User \"" + username
                                + "\" could still be loaded from test data source via UserService.loadUser");

                for (User remainingUser : userService.getAllUsers()) {
                        Assertions.assertNotEquals(toBeDeletedUser.getUsername(), remainingUser.getUsername(),
                                        "Deleted User \""
                                                        + username
                                                        + "\" could still be loaded from test data source via UserService.getAllUsers");
                }

        }

        @DirtiesContext
        @Test
        @WithMockUser(username = "admin", authorities = { "ADMIN" })
        public void compareToTest() {
                User loadedUser_1 = userService.loadUser("liza");
                User loadedUser_2 = userService.loadUser("test_user");
                int result_1 = loadedUser_1.compareTo(loadedUser_1);
                int result_2 = loadedUser_2.compareTo(loadedUser_1);
                Assertions.assertEquals(0, result_1, "Users are equal");
                Assertions.assertNotEquals(0, result_2, "Users are not equal");

        }

        @DirtiesContext
        @Test
        @WithMockUser(username = "admin", authorities = { "ADMIN" })
        public void createUserTest() {
                User loadedUser = userService.loadUser("liza");
                userService.createUser(loadedUser);

                Assertions.assertEquals(8, userService.getAllUsers().size(),
                                "Insufficient amount of users initialized for test data source");

        }

}
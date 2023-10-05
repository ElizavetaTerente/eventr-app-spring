package at.qe.skeleton.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.web.WebAppConfiguration;

import at.qe.skeleton.model.UserRole;
import at.qe.skeleton.services.UserService;
import at.qe.skeleton.ui.beans.SessionInfoBean;

/**
 * Some very basic tests for {@link UserService}.
 *
 * This class is part of the skeleton project provided for students of the
 * courses "Software Architecture" and "Software Engineering" offered by the
 * University of Innsbruck.
 */
@SpringBootTest
@WebAppConfiguration
public class SessionInfoBeanTest {

    @Autowired
    SessionInfoBean sessionInfoBean;

    @Autowired
    UserService userService;

    @Test
    @WithMockUser(username = "user1", authorities = { "MANAGER" })
    public void testLoggedIn() {
        Assertions.assertTrue(sessionInfoBean.isLoggedIn(),
                "sessionInfoBean.isLoggedIn does not return true for authenticated user");
        Assertions.assertEquals("user1", sessionInfoBean.getCurrentUserName(),
                "sessionInfoBean.getCurrentUserName does not return authenticated user's name");
        Assertions.assertEquals("user1", sessionInfoBean.getCurrentUserName(),
                "sessionInfoBean.getCurrentUser does not return authenticated user");
        Assertions.assertEquals("MANAGER", sessionInfoBean.getCurrentUserRoles(),
                "sessionInfoBean.getCurrentUserRoles does not return authenticated user's roles");
        Assertions.assertTrue(sessionInfoBean.hasRole("MANAGER"),
                "sessionInfoBean.hasRole does not return true for a role the authenticated user has");
        Assertions.assertFalse(sessionInfoBean.hasRole("ADMIN"),
                "sessionInfoBean.hasRole does not return false for a role the authenticated user does not have");
    }

    @Test
    @WithMockUser(username = "anonymousUser")
    public void testLoggedInAsAnonymousUser() {
        Assertions.assertFalse(sessionInfoBean.isLoggedIn(),
                "sessionInfoBean.isLoggedIn return false for authenticated user name = anonymousUser");
    }

    @Test
    @WithMockUser(username = "anonymousUser", authorities = { "null" })
    public void testHasRoleNull() {
        Assertions.assertFalse(sessionInfoBean.hasRole("USER"),
                "sessionInfoBean.isLoggedIn return false for authenticated user name = anonymousUser");
    }

    @Test
    @WithMockUser(username = "anonymousUser")
    public void testHasEmptyRole() {
        Assertions.assertFalse(sessionInfoBean.hasRole("USER"),
                "sessionInfoBean.isLoggedIn return false for authenticated user name = anonymousUser");
    }

    @Test
    public void testNotLoggedIn() {
        Assertions.assertFalse(sessionInfoBean.isLoggedIn(),
                "sessionInfoBean.isLoggedIn does return true for not authenticated user");
        Assertions.assertEquals("", sessionInfoBean.getCurrentUserName(),
                "sessionInfoBean.getCurrentUserName does not return empty string when not logged in");
        Assertions.assertNull(sessionInfoBean.getCurrentUser(),
                "sessionInfoBean.getCurrentUser does not return null when not logged in");
        Assertions.assertEquals("", sessionInfoBean.getCurrentUserRoles(),
                "sessionInfoBean.getCurrentUserRoles does not return empty string when not logged in");
        for (UserRole role : UserRole.values()) {
            Assertions.assertFalse(sessionInfoBean.hasRole(role.name()),
                    "sessionInfoBean.hasRole does not return false for all possible roles");
        }
    }

    @Test
    @WithMockUser(username = "liza", authorities = { "USER" })
    public void getCurrentUserNotNullTest() {

        Assertions.assertNotNull(sessionInfoBean.getCurrentUser());
    }

}
package at.qe.skeleton.tests;

import at.qe.skeleton.model.User;
import at.qe.skeleton.model.UserRole;
import at.qe.skeleton.services.UserService;
import at.qe.skeleton.ui.beans.SessionInfoBean;
import at.qe.skeleton.ui.controllers.SignUpController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@WebAppConfiguration
public class SignUpControllerTest {

    @Autowired
    SignUpController signUpController;

    @Autowired
    UserService userService;

    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void setAndGetRegistrationTest(){

        String username = "liza";

        Assertions.assertNull(signUpController.getRegistrationUser().getId());
        User loadedUser = userService.loadUser(username);
        signUpController.setRegistrationUser(loadedUser);
        Assertions.assertEquals(signUpController.getRegistrationUser().getUsername(),username, "User which was set as RegistrationUser is not matching with its name");
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void doRegisterTest(){

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
        toBeCreatedUser.setRoles(Sets.newSet(UserRole.USER));

        signUpController.setRegistrationUser(toBeCreatedUser);
        String result = signUpController.doRegister();
        Assertions.assertEquals("login.xhtml?signedup=true&faces-redirect=true", result, "returned false xhtml");


    }

}

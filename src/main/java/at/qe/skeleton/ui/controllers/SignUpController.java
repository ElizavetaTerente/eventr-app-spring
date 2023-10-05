package at.qe.skeleton.ui.controllers;

import at.qe.skeleton.model.User;
import at.qe.skeleton.repositories.UserRepository;
import at.qe.skeleton.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@Component
@Scope("request")
public class SignUpController implements Serializable {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private User registrationUser;

    public SignUpController() {
        this.registrationUser = new User();
    }

    public User getRegistrationUser() {
        return registrationUser;
    }

    public void setRegistrationUser(User registrationUser) {
        this.registrationUser = registrationUser;
    }

    public String doRegister() {
        if (userRepository.findFirstByUsername(registrationUser.getUsername()) == null) {
            userService.createUser(registrationUser);

            return "login.xhtml?signedup=true&faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage("registrationForm:error",
                    new FacesMessage("Username already in use!"));
            return "signup.xhtml";
        }
    }
}

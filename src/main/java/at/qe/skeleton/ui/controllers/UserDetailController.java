package at.qe.skeleton.ui.controllers;

import at.qe.skeleton.model.User;
import at.qe.skeleton.model.UserRole;
import at.qe.skeleton.services.UserService;
import java.io.Serializable;
import java.util.*;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controller for the user detail view.
 *
 * This class is part of the skeleton project provided for students of the
 * courses "Software Architecture" and "Software Engineering" offered by the
 * University of Innsbruck.
 */
@Component
@Scope("view")
public class UserDetailController implements Serializable {

    @Autowired
    private UserService userService;

    private User user;
    private String mode;

    public void setUser(User user) {
        this.user = user;
        doReloadUser();
    }

    public User getUser() {
        return user;
    }

    public void doReloadUser() {
        user = userService.loadUser(user.getUsername());
    }

    public void setMode(String mode) {
        if (mode.equals("add")) {
            this.user = new User();

            Set<UserRole> roles = new HashSet<UserRole>();
            roles.add(UserRole.USER);

            user.setRoles(roles);
        }

        this.mode = mode;
    }

    public List<UserRole> getSelectedRoles() {
        return new ArrayList<>(user.getRoles());
    }

    public void setSelectedRoles(List<UserRole> roles) {
        Set<UserRole> rolez = new HashSet<UserRole>();
        for (UserRole role : roles) {
            rolez.add(role);
        }
        user.setRoles(rolez);
    }

    public List<UserRole> getAvailableRoles() {
        return Arrays.asList(UserRole.values());
    }

    public String getMode() {
        return this.mode;
    }

    public void doSaveUser() {
        user = this.userService.saveUser(user);
        this.user = null;

        PrimeFaces.current().executeScript("PF('userEditDialog').hide()");
    }

    public void doDeleteUser() {
        this.userService.deleteUser(user);
        user = null;
    }

    public void toggleEnabled(User user) {
        user.setEnabled(user.isEnabled());
        this.userService.saveUser(user);
    }

}

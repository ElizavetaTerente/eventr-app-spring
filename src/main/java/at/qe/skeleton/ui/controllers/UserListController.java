package at.qe.skeleton.ui.controllers;

import at.qe.skeleton.model.Location;
import at.qe.skeleton.model.User;
import at.qe.skeleton.services.UserService;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controller for the user list view.
 *
 * This class is part of the skeleton project provided for students of the
 * courses "Software Architecture" and "Software Engineering" offered by the
 * University of Innsbruck.
 */
@Component
@Scope("view")
public class UserListController implements Serializable {

    @Autowired
    private UserService userService;

    private List<User> users;
    private List<User> filteredUsers;

    @PostConstruct
    public void init() {
        this.users = userService.getAllUsers();
    }

    /**
     * Returns a list of all users.
     *
     * @return
     */
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getActiveUsers() {
        return userService.getActiveUsers();
    }

    public List<User> getFilteredUsers() {
        return filteredUsers;
    }

    public void setFilteredUsers(List<User> filteredUsers) {
        this.filteredUsers = filteredUsers;
    }

}

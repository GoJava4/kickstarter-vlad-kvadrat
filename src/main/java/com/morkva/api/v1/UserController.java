package com.morkva.api.v1;

import com.morkva.entities.Project;
import com.morkva.entities.User;
import com.morkva.services.UserDetailsServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "restUserController")
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserDetailsServiceExtended userService;

    @RequestMapping(value = "/{id]", method = RequestMethod.GET)
    public User getUserById(@PathVariable int id) {
        return userService.getById(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createUser(@RequestParam(value = "login", required = true) String login,
                           @RequestParam(value = "password", required = true) String password,
                           @RequestParam(value = "email", required = true) String email,
                           @RequestParam(value = "username", required = true) String username,
                           @RequestParam(value = "personalInfo", required = true) String personalInfo) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setUsername(username);
        user.setPersonalInfo(personalInfo);
        userService.create(user);
    }

    @RequestMapping(value = "/{id]/update", method = RequestMethod.PUT)
    public void updateUserById(@PathVariable int id,
                               @RequestParam(value = "login", required = false) String login,
                               @RequestParam(value = "password", required = false) String password,
                               @RequestParam(value = "email", required = false) String email,
                               @RequestParam(value = "username", required = false) String username,
                               @RequestParam(value = "personalInfo", required = false) String personalInfo) {
        User user = userService.getById(id);
        if (login != null) {user.setLogin(login);}
        if (password != null) {user.setPassword(password);}
        if (email != null) {user.setEmail(email);}
        if (username != null) {user.setUsername(username);}
        if (personalInfo != null) {user.setPersonalInfo(personalInfo);}
        userService.create(user);
    }

    @RequestMapping(value = "/{id]/delete", method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable int id) {
    }

    @RequestMapping(value = "/{id}/getAllProjects", method = RequestMethod.GET)
    public List<Project> getAllProjectsOfUser(@PathVariable int id) {
        return null;
    }

    @RequestMapping(value = "/{id}/getAllFinishedProjects", method = RequestMethod.GET)
    public List<Project> getAllFinishedProjectsOfUser(@PathVariable int id) {
        return null;
    }
}

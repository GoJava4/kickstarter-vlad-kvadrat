package com.morkva.api.v1;

import com.morkva.entities.Project;
import com.morkva.entities.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "restUserController")
@RequestMapping("/api/v1/user")
public class UserController {

    @RequestMapping(value = "/{id]", method = RequestMethod.GET)
    public User getUserById(@PathVariable int id) {
        return null;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createUser(@RequestParam(value = "login", required = true) String login,
                           @RequestParam(value = "password", required = true) String password,
                           @RequestParam(value = "email", required = true) String email,
                           @RequestParam(value = "username", required = true) String username,
                           @RequestParam(value = "personalInfo", required = true) String personalInfo) {

    }

    @RequestMapping(value = "/{id]/update", method = RequestMethod.PUT)
    public void updateUserById(@PathVariable int id,
                               @RequestParam(value = "login", required = false) String login,
                               @RequestParam(value = "password", required = false) String password,
                               @RequestParam(value = "email", required = false) String email,
                               @RequestParam(value = "username", required = false) String username,
                               @RequestParam(value = "personalInfo", required = false) String personalInfo) {

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

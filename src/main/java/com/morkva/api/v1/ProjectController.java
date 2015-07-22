package com.morkva.api.v1;

import com.morkva.entities.Category;
import com.morkva.entities.FullDescription;
import com.morkva.entities.Project;
import com.morkva.entities.User;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Project getProjectById(@PathVariable int id) {
        return null;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createProject(@RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "shortDescr", required = true) String shortDescr,
                              @RequestParam(value = "neededMoney", required = true) int neededMoney,
                              @RequestParam(value = "endingDate", required = true) Date endingDate,
                              @RequestParam(value = "user", required = true) User user,
                              @RequestParam(value = "category", required = true) Category category,
                              @RequestParam(value = "fullDescr", required = true) FullDescription fullDescription) {
        //create project,
        // mb return id?
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public void updateProjectById(@PathVariable int id,
                                  @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "shortDescr", required = false) String shortDescr,
                                  @RequestParam(value = "neededMoney", required = false) int neededMoney,
                                  @RequestParam(value = "endingDate", required = false) Date endingDate,
                                  @RequestParam(value = "user", required = false) User user,
                                  @RequestParam(value = "category", required = false) Category category,
                                  @RequestParam(value = "fullDescr", required = false) FullDescription fullDescription,
                                  @RequestParam(value = "finished", required = false) boolean finished) {
        //update project by id
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteProjectById(@PathVariable int id) {
        //delete project by id
    }

    @RequestMapping(value = "/getCurrentMoneyOfProject/{id}", method = RequestMethod.GET)
    public double getCurrentMoneyOfProject(@PathVariable int id) {
        //getCurrentMoneyOfProject
        //less info to return
        return 0;
    }

    @RequestMapping(value = "/getAllProjectsOfCategory/{id}", method = RequestMethod.GET)
    public List<Project> getAllProjectsOfCategoryById(@PathVariable int id) {
        return null;
    }

    @RequestMapping(value = "/getAllProjectsOfUser/{id}", method = RequestMethod.GET)
    public List<Project> getAllProjectsOfUserById(@PathVariable int id) {
        return null;
    }

    @RequestMapping(value = "/getAllFinishedProjects", method = RequestMethod.GET)
    public List<Project> getAllFinishedProjects() {
        return null;
    }

    @RequestMapping(value = "/getAllFinishedProjectsOfCategory/{id}", method = RequestMethod.GET)
    public List<Project> getAllFinishedProjectsOfCategory(@PathVariable int id) {
        return null;
    }

    @RequestMapping(value = "/getAllFinishedProjectsOfUser/{id}", method = RequestMethod.GET)
    public List<Project> getAllFinishedProjectsOfUser(@PathVariable int id) {
        return null;
    }

}

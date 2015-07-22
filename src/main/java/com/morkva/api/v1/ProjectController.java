package com.morkva.api.v1;

import com.morkva.entities.Category;
import com.morkva.entities.FullDescription;
import com.morkva.entities.Project;
import com.morkva.entities.User;
import com.morkva.services.CategoryService;
import com.morkva.services.ProjectService;
import com.morkva.services.UserDetailsServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController(value = "restProjectController")
@RequestMapping("/api/v1/project")
public class ProjectController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserDetailsServiceExtended userService;

    @Autowired
    ProjectService projectService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Project getProjectById(@PathVariable int id) {
        return projectService.getById(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createProject(@RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "shortDescr", required = true) String shortDescr,
                              @RequestParam(value = "neededMoney", required = true) int neededMoney,
                              @RequestParam(value = "endingDate", required = true) Date endingDate,
                              @RequestParam(value = "user", required = true) User user,
                              @RequestParam(value = "category", required = true) Category category,
                              @RequestParam(value = "fullDescr", required = true) FullDescription fullDescription) {
        Project project = new Project.Builder()
                .setName(name)
                .setShortDescr(shortDescr)
                .setNeedMoney(neededMoney)
                .setEndingDate(endingDate)
                .setUser(user)
                .setCategory(category)
                .setFullDescription(fullDescription)
                .build();
        projectService.simpleCreate(project);
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.PUT)
    public void updateProjectById(@PathVariable int id,
                                  @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "shortDescr", required = false) String shortDescr,
                                  @RequestParam(value = "neededMoney", required = false) Integer neededMoney,
                                  @RequestBody(required = false) Date endingDate,
                                  @RequestBody(required = false) User user,
                                  @RequestBody(required = false) Category category,
                                  @RequestBody(required = false) FullDescription fullDescription,
                                  @RequestParam(value = "finished", required = false) Boolean finished) {
        Project project = projectService.getById(id);
        if (name == null) {name = project.getName();}
        if (name == null) {shortDescr = project.getShortDescr();}
        if (name == null) {neededMoney = project.getNeedMoney();}
        if (name == null) {endingDate = project.getEndingDate();}
        if (name == null) {user = project.getUser();}
        if (name == null) {category = project.getCategory();}
        if (name == null) {fullDescription = project.getFullDescription();}
        if (name == null) {finished = project.isSuccessfullyFinished();}

        project.setName(name);
        project.setShortDescr(shortDescr);
        project.setNeedMoney(neededMoney);
        project.setEndingDate(endingDate);
        project.setUser(user);
        project.setCategory(category);
        project.setFullDescription(fullDescription);
        project.setSuccessfullyFinished(finished);
        projectService.update(project);
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public void deleteProjectById(@PathVariable int id) {
        Project project = projectService.getById(id);
        projectService.delete(project);
    }

    @RequestMapping(value = "/{id}/getCurrentMoney", method = RequestMethod.GET)
    public double getCurrentMoneyOfProject(@PathVariable int id) {
        Project project = projectService.getById(id);
        //less info to return than just get all project info
        return project.getCurrentMoney();
    }

    @RequestMapping(value = "/getAllProjectsOfCategory/{categoryId}", method = RequestMethod.GET)
    public List<Project> getAllProjectsOfCategoryById(@PathVariable int categoryId) {
        Category category = categoryService.getById(categoryId);
        return projectService.getProjectsOf(category);
    }

    @RequestMapping(value = "/getAllProjectsOfUser/{userId}", method = RequestMethod.GET)
     public List<Project> getAllProjectsOfUserById(@PathVariable int userId) {
        User user = userService.getById(userId);
        return projectService.getProjectsOf(user);
    }

    @RequestMapping(value = "/getAllFinishedProjects", method = RequestMethod.GET)
    public List<Project> getAllFinishedProjects() {
        return projectService.getAllFinishedProjects();
    }

    @RequestMapping(value = "/getAllFinishedProjectsOfCategory/{categoryId}", method = RequestMethod.GET)
    public List<Project> getAllFinishedProjectsOfCategory(@PathVariable int categoryId) {
        Category category = categoryService.getById(categoryId);
        return projectService.getFinishedProjectsOf(category);
    }

    @RequestMapping(value = "/getAllFinishedProjectsOfUser/{userId}", method = RequestMethod.GET)
    public List<Project> getAllFinishedProjectsOfUser(@PathVariable int userId) {
        User user = userService.getById(userId);
        return projectService.getFinishedProjectsOf(user);
    }

    @RequestMapping(value = "/getAllNotFinishedProjectsOfCategory/{categoryId}", method = RequestMethod.GET)
    public List<Project> getAllNotFinishedProjectsOfCategory(@PathVariable int categoryId) {
        Category category = categoryService.getById(categoryId);
        return projectService.getNotFinishedProjectsOf(category);
    }

    @RequestMapping(value = "/getAllNotFinishedProjectsOfUser/{userId}", method = RequestMethod.GET)
    public List<Project> getAllNotFinishedProjectsOfUser(@PathVariable int userId) {
        User user = userService.getById(userId);
        return projectService.getNotFinishedProjectsOf(user);
    }

}

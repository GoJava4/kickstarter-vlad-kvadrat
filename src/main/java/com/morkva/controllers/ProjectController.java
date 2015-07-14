package com.morkva.controllers;

import com.morkva.entities.Project;
import com.morkva.services.ProjectService;
import com.morkva.services.UserDetailsServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserDetailsServiceExtended userDetailsService;

    @RequestMapping(method = RequestMethod.GET)
    public String showProject(ModelMap modelMap, @RequestParam int projectId) {
        Project project = projectService.getById(projectId);
        modelMap.addAttribute("project", project);

        return "project";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String donateToProject(ModelMap modelMap, @RequestParam int donateCount, @RequestParam int projectId) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        com.morkva.entities.User userByLogin = userDetailsService.getUserByLogin(user.getUsername());

        Project project = projectService.donate(projectId, donateCount, userByLogin);

        modelMap.addAttribute("project", project);
        return "project";
    }
}

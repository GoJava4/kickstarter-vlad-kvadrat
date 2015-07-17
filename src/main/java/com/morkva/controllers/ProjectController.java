package com.morkva.controllers;

import com.morkva.entities.Comment;
import com.morkva.entities.Project;
import com.morkva.services.CommentService;
import com.morkva.services.ProjectService;
import com.morkva.services.UserDetailsServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserDetailsServiceExtended userDetailsService;

    @Qualifier("commentService")
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "{projectId}", method = RequestMethod.GET)
    public String showProject(ModelMap modelMap, @PathVariable int projectId) {
        Project project = projectService.getById(projectId);
        List<Comment> comments = commentService.getCommentsOfProject(project);
        modelMap.addAttribute("project", project);
        modelMap.addAttribute("comments", comments);
        return "project";
    }

    @RequestMapping(value = "{projectId}/donate", method = RequestMethod.POST)
    public String donateToProject(ModelMap modelMap, @RequestParam int donateCount, @PathVariable int projectId) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        com.morkva.entities.User userByLogin = userDetailsService.getUserByLogin(user.getUsername());

        Project project = projectService.donate(projectId, donateCount, userByLogin);

        modelMap.addAttribute("project", project);
        return "redirect:/project/" + projectId;
    }

    @RequestMapping(value = "{projectId}/comments/add", method = RequestMethod.POST)
    public String addComment(ModelMap modelMap, @PathVariable int projectId, @RequestParam(value = "comment", required = true) String commentValue) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.morkva.entities.User userByLogin = userDetailsService.getUserByLogin(user.getUsername());

        Project project = projectService.getById(projectId);
        commentService.create(project, userByLogin, new Date(), commentValue);

        return "redirect:/project/" + projectId;
    }
}

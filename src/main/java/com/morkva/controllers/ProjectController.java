package com.morkva.controllers;

import com.morkva.entities.Comment;
import com.morkva.entities.Payment;
import com.morkva.entities.Project;
import com.morkva.services.impl.CommentServiceImpl;
import com.morkva.services.impl.ProjectServiceImpl;
import com.morkva.services.UserDetailsServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectServiceImpl projectService;

    @Autowired
    private UserDetailsServiceExtended userDetailsService;

    @Qualifier("commentService")
    @Autowired
    private CommentServiceImpl commentService;


    @RequestMapping(value = "{projectId}", method = RequestMethod.GET)
    public String showProject(Model model, @PathVariable int projectId) {
        Project project = projectService.getById(projectId);
        List<Comment> comments = commentService.getCommentsOfProject(project);

        if (!model.containsAttribute("payment")) {
            model.addAttribute("payment", new Payment());
        }
        model.addAttribute("project", project);
        model.addAttribute("comments", comments);
        return "project";
    }

    @RequestMapping(value = "{projectId}/donate", method = RequestMethod.POST)
    public String donateToProject(
            @Valid @ModelAttribute("payment") Payment payment,
            BindingResult bindingResult,
            @PathVariable int projectId,
            RedirectAttributes attributes
    ) {

        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.payment", bindingResult);
            attributes.addFlashAttribute("payment", payment);
            return "redirect:/project/" + projectId;
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        com.morkva.entities.User userByLogin = userDetailsService.getUserByLogin(user.getUsername());

        projectService.donate(projectId, payment.getAmount(), userByLogin);
        return "redirect:/project/" + projectId;
    }

    @RequestMapping(value = "{projectId}/comments/add", method = RequestMethod.POST)
    public String addComment(@PathVariable int projectId, @RequestParam(value = "comment", required = true) String commentValue) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.morkva.entities.User userByLogin = userDetailsService.getUserByLogin(user.getUsername());

        Project project = projectService.getById(projectId);
        commentService.create(project, userByLogin, new Date(), commentValue);

        return "redirect:/project/" + projectId;
    }
}

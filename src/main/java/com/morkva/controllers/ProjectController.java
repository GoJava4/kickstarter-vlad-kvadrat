package com.morkva.controllers;

import com.morkva.entities.*;
import com.morkva.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserDetailsServiceExtended userDetailsService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private FullDescriptionService fullDescriptionService;


    @RequestMapping(value = "{projectId}", method = RequestMethod.GET)
    public String showProject(Model model, @PathVariable int projectId) {
        Project project = projectService.getById(projectId);
        List<Comment> comments = commentService.getCommentsOfProject(project);
        List<Question> questions = questionService.getQuestionsOfProject(project);


        if (!model.containsAttribute("payment")) {
            model.addAttribute("payment", new Payment());
        }
        model.addAttribute("questions", questions);
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

        Project project = projectService.getById(projectId);
        commentService.create(project, getCurrentUser(), new Date(), commentValue);

        return "redirect:/project/" + projectId;
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addProject(ModelMap model, @RequestParam(value = "category_id") int categoryId) {
        Project project = new Project();
        model.addAttribute("category_id", categoryId);
        model.addAttribute("project", project);
        return "addProject";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addProject(@Valid @ModelAttribute("project") Project project,
                             BindingResult bindingResult,
                             @RequestParam(value = "category_id") int categoryId) {
        if (bindingResult.hasErrors()) {
            return "addProject";
        }

        Category category = categoryService.getById(categoryId);

        Project projectToAdd = new Project.Builder()
                .setAddingDate(new Date())
                .setCategory(category)
                .setCurrentMoney(0)
                .setSuccessfullyFinished(false)
                .setUser(getCurrentUser())
                .setName(project.getName())
                .setEndingDate(project.getEndingDate())
                .setFullDescription(project.getFullDescription())
                .setNeedMoney(project.getNeedMoney())
                .setShortDescr(project.getShortDescr())
                .build();
        projectService.create(projectToAdd);
        return "redirect:/project/" + projectToAdd.getId();
    }

    private User getCurrentUser() {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetailsService.getUserByLogin(user.getUsername());
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


}

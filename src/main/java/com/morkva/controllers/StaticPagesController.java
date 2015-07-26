package com.morkva.controllers;

import com.morkva.entities.Category;
import com.morkva.entities.User;
import com.morkva.entities.UserRole;
import com.morkva.services.CategoryService;
import com.morkva.services.QuoteService;
import com.morkva.services.UserDetailsServiceExtended;
import com.morkva.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StaticPagesController {

    @Autowired
    QuoteService quoteService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserDetailsServiceExtended userDetailsService;

    @Autowired
    UserRoleService userRoleService;

    @RequestMapping(value = {"/", "/home", "/categories"}, method = RequestMethod.GET)
    public String showHome(ModelMap modelMap) {
        //TODO create home page
        return "redirect:/category";
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public String showContacts() {
        return "contacts";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String showTestPage() {
        return "loginPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage(@RequestParam(value = "error", required = false) String error, ModelMap modelMap) {
        if(error != null) {
            modelMap.addAttribute("error", "Invalid username or password");
        }

        return "loginPage";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String showSignUpPage(ModelMap modelMap) {
        if (!modelMap.containsAttribute("user")) {
            modelMap.addAttribute("user", new User());
        }

        return "SignUp";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp(@Valid @ModelAttribute("user") User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "SignUp";
        }

        UserRole userRole = userRoleService.getById(2);
        user.setActive(true);
        user.setRole(userRole);
        userDetailsService.create(user);

        return "redirect:/home";
    }

    @InitBinder     /* Converts empty strings into null when a form is submitted */
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @ModelAttribute("allCategories")
    public List<Category> getAllCategories() {
        return categoryService.getAll();
    }
}

package com.morkva.controllers;

import com.morkva.entities.Category;
import com.morkva.entities.User;
import com.morkva.services.CategoryService;
import com.morkva.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StaticPagesController {

    @Autowired
    QuoteService quoteService;

    @Autowired
    CategoryService categoryService;

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
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "SignUp";
        }

        //TODO Signup

        return "SignUp";
    }

    @ModelAttribute("allCategories")
    public List<Category> getAllCategories() {
        return categoryService.getAll();
    }
}

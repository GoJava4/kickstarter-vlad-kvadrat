package com.morkva.controllers;

import com.morkva.entities.Category;
import com.morkva.services.CategoryService;
import com.morkva.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @ModelAttribute("allCategories")
    public List<Category> getAllCategories() {
        return categoryService.getAll();
    }
}

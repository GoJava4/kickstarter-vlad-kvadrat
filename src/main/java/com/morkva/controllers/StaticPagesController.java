package com.morkva.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StaticPagesController {

    @RequestMapping(value = {"/", "/home", "/categories"}, method = RequestMethod.GET)
    public String showHome(ModelMap modelMap) {
        //TODO create home page
        return "redirect:/category";
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public String showContacts() {
        return "contacts";
    }

}

package com.morkva.controllers;

import com.morkva.services.CategoryService;
import com.morkva.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by koros on 19.06.2015.
 */
@Controller
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    QuoteService quoteService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public String showCategories(ModelMap model) {

        model.addAttribute("quote", quoteService.getRandom());
        model.addAttribute("list", categoryService.getAll());
        return "categories";
    }
}

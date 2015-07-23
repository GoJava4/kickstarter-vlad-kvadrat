package com.morkva.controllers;

import com.morkva.entities.Category;
import com.morkva.entities.Project;
import com.morkva.exceptions.NotFound404Exception;
import com.morkva.services.CategoryService;
import com.morkva.services.ProjectService;
import com.morkva.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private QuoteService quoteService;

    @RequestMapping(method = RequestMethod.GET)
    public String showCategories(ModelMap model) {

        model.addAttribute("quote", quoteService.getRandom());
        model.addAttribute("list", categoryService.getAll());
        return "categories";
    }

    @RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
    public String showCategory(ModelMap modelMap, @PathVariable int categoryId) {
        Category category = categoryService.getById(categoryId);

        List<Project> projectsForCategory = projectService.getProjectsOfCategory(category);

        modelMap.addAttribute("projects", projectsForCategory);
        modelMap.addAttribute("category_name", category.getName());
        modelMap.addAttribute("category_id", categoryId);

        return "category";
    }

    @RequestMapping(value = "/{categoryId}/add", method = RequestMethod.GET)
    public String addCategory(@PathVariable int categoryId) {
        if (categoryService.getById(categoryId) == null) {
            throw new NotFound404Exception();
        }
        return "category";
    }

    @RequestMapping(value = "/{categoryId}/delete", method = RequestMethod.GET)
    public String deleteCategory(@PathVariable int categoryId) {
        Category category = categoryService.getById(categoryId);
        categoryService.delete(category);
        return "redirect:/categories";
    }

    @ModelAttribute("allCategories")
    public List<Category> getAllCategories() {
        return categoryService.getAll();
    }
}

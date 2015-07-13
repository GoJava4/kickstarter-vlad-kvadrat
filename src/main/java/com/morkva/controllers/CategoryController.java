package com.morkva.controllers;

import com.morkva.entities.Category;
import com.morkva.entities.Project;
import com.morkva.exceptions.NotFound404Exception;
import com.morkva.services.CategoryService;
import com.morkva.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by koros on 26.06.2015.
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    ProjectService projectService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
    public String showCategory(ModelMap modelMap, @PathVariable String categoryId) {
        int id = Integer.parseInt(categoryId);
        Category category = categoryService.getById(id);

        List<Project> projectsForCategory = projectService.getProjectsOfCategory(category);

        modelMap.addAttribute("projects", projectsForCategory);
        modelMap.addAttribute("category_name", category.getName());
        modelMap.addAttribute("category_id", categoryId);

        return "category";
    }

    @RequestMapping(value = "/{categoryId}/add", method = RequestMethod.GET)
    public String addCategory(ModelMap modelMap, @PathVariable int categoryId) {
        if (categoryService.getById(categoryId) == null) {
            throw new NotFound404Exception();
        }
        return "category";
    }

    @RequestMapping(value = "/{categoryId}/delete", method = RequestMethod.POST)
    public String deleteCategory(ModelMap modelMap, @PathVariable int categoryId) {
        Category category = categoryService.getById(categoryId);
        categoryService.delete(category);
        return "redirect:/categories";
    }
}

package com.morkva.api.v1;

import com.morkva.entities.Category;
import com.morkva.entities.Project;
import com.morkva.services.CategoryService;
import com.morkva.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "restCategoryController")
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProjectService projectService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Category getCategoryById (@PathVariable int id){
        return categoryService.getById(id);
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.PUT)
    public void updateCategoryById (@PathVariable int id, @RequestParam String name){
        Category category = categoryService.getById(id);
        category.setName(name);
        categoryService.update(category);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createCategory (@RequestParam String name){
        Category category = new Category();
        category.setName(name);
        categoryService.create(category);
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public void deleteCategoryById (@PathVariable int id, @RequestParam String name){
        Category category = categoryService.getById(id);
        categoryService.delete(category);
    }

    @RequestMapping(value = "/{id}/getAllProjects", method = RequestMethod.GET)
    public List<Project> getAllProjectsOfCategoryById (@PathVariable int id){
        Category category = categoryService.getById(id);
        return projectService.getProjectsOfCategory(category);
    }
}
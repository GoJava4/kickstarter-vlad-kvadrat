package com.morkva.api.v1;

import com.morkva.entities.Category;
import com.morkva.entities.Project;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "restCategoryController")
@RequestMapping("/api/v1/category")
public class CategoryController {

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Category getCategoryById (@PathVariable int id){
        return null;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public void updateCategoryById (@PathVariable int id, @RequestParam String name){
        //update category name by id
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createCategory (@RequestParam String name){
        //create category using name
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteCategoryById (@PathVariable int id, @RequestParam String name){
        //delete category by id
    }

    @RequestMapping(value = "/{id}/getAllProjects", method = RequestMethod.GET)
    public List<Project> getAllProjectsOfCategoryById (@PathVariable int id){
        //get all projects of the category
        return null;
    }
}

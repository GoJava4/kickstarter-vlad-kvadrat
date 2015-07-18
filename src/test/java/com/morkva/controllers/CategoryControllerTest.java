package com.morkva.controllers;

import com.morkva.entities.Category;
import com.morkva.entities.Project;
import com.morkva.services.CategoryService;
import com.morkva.services.ProjectService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class CategoryControllerTest {

    @Mock
    ProjectService projectService;

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShowCategory() throws Exception {
        Category expectedCategory = new Category();
        List<Project> expectedProjectsOfCategory = Arrays.asList(new Project(), new Project(), new Project());
        
    }

    @Test
    public void testAddCategory() throws Exception {

    }

    @Test
    public void testDeleteCategory() throws Exception {

    }
}
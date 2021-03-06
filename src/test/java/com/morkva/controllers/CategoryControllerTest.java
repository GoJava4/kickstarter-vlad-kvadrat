package com.morkva.controllers;

import com.morkva.entities.Category;
import com.morkva.entities.Project;
import com.morkva.entities.Quote;
import com.morkva.services.CategoryService;
import com.morkva.services.ProjectService;
import com.morkva.services.QuoteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;

import java.util.Arrays;
import java.util.List;

public class CategoryControllerTest {

    @Mock
    ProjectService projectService;

    @Mock
    CategoryService categoryService;

    @Mock
    QuoteService quoteService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @Before
     public void init() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void testShowCategory() throws Exception {
        Category expectedCategory = Mockito.mock(Category.class);
        Mockito.when(expectedCategory.getId()).thenReturn(1);
        Mockito.when(expectedCategory.getName()).thenReturn("Name");

        List<Project> expectedProjectsOfCategory = Arrays.asList(new Project(), new Project(), new Project());

        Mockito.when(categoryService.getById(1)).thenReturn(expectedCategory);
        Mockito.when(projectService.getProjectsOf(expectedCategory)).thenReturn(expectedProjectsOfCategory);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/category/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("category_name", expectedCategory.getName()))
                .andExpect(MockMvcResultMatchers.model().attribute("category_id", expectedCategory.getId()))
                .andExpect(MockMvcResultMatchers.model().attribute("projects", expectedProjectsOfCategory));
    }

    @Test
    public void testAddCategory() throws Exception {

    }

    @Test
    public void testDeleteCategory() throws Exception {

    }

    @Test
    public void testShowCategories() throws Exception {
        Quote expectedQuote = new Quote();
        List<Category> expectedCategories = Arrays.asList(new Category(), new Category(), new Category());
        Mockito.when(quoteService.getRandom()).thenReturn(expectedQuote);
        Mockito.when(categoryService.getAll()).thenReturn(expectedCategories);

        ModelMap model = new ModelMap();

        String viewName = categoryController.showCategories(model);

        Assert.assertEquals("categories", viewName);

        Assert.assertSame(expectedQuote, model.get("quote"));
        Assert.assertSame(expectedCategories, model.get("list"));
        Mockito.verify(quoteService).getRandom();
        Mockito.verify(categoryService).getAll();
    }
}
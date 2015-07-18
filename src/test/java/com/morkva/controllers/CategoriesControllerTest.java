package com.morkva.controllers;

import com.morkva.entities.Category;
import com.morkva.entities.Quote;
import com.morkva.services.CategoryService;
import com.morkva.services.QuoteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;

import java.util.Arrays;
import java.util.List;


public class CategoriesControllerTest {

    @Mock
    QuoteService quoteService;

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoriesController categoriesController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShowCategories() throws Exception {
        Quote expectedQuote = new Quote();
        List<Category> expectedCategories = Arrays.asList(new Category(), new Category(), new Category());
        Mockito.when(quoteService.getRandom()).thenReturn(expectedQuote);
        Mockito.when(categoryService.getAll()).thenReturn(expectedCategories);

        ModelMap model = new ModelMap();

        String viewName = categoriesController.showCategories(model);

        Assert.assertEquals("categories", viewName);

        Assert.assertSame(expectedQuote, model.get("quote"));
        Assert.assertSame(expectedCategories, model.get("list"));
        Mockito.verify(quoteService).getRandom();
        Mockito.verify(categoryService).getAll();
    }
}
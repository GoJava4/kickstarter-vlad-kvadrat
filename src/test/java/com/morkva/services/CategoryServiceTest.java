package com.morkva.services;

import com.morkva.entities.Category;
import com.morkva.model.dao.CategoryDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;

import static org.mockito.Mockito.when;

/**
 * Created by koros on 30.06.2015.
 */
public class CategoryServiceTest {

    @Mock
    CategoryDao categoryDAO;

    @InjectMocks
    CategoryService categoryService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() throws Exception {
        when(categoryDAO.getAll()).thenReturn(new LinkedList<Category>());
        Assert.assertTrue(categoryService.getAll().size() == 0);
    }

    @Test
    public void testGetById() throws Exception {
        Category category = new Category("Mocked category");
        when(categoryDAO.getById(1)).thenReturn(category);
        Assert.assertEquals(category, categoryService.getById(1));
    }
}
package com.morkva.services.impl;

import com.morkva.entities.Category;
import com.morkva.model.dao.CategoryDao;
import com.morkva.services.impl.CategoryServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {

    @Mock
    CategoryDao categoryDAO;

    @InjectMocks
    CategoryServiceImpl categoryService;

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

    @Test
    public void testDelete() throws Exception {
        Category category = new Category("Mocked category");
        categoryService.delete(category);
        verify(categoryDAO).delete(category);
    }
}
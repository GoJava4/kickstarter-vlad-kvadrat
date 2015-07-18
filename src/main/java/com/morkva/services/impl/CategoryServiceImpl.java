package com.morkva.services.impl;

import com.morkva.entities.Category;
import com.morkva.model.dao.CategoryDao;
import com.morkva.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDAO;

    @Override
    public List<Category> getAll() {
        return categoryDAO.getAll();
    }

    @Override
    public Category getById(Integer categoryId) {
        return categoryDAO.getById(categoryId);
    }

    @Override
    public void delete(Category category) {
        categoryDAO.delete(category);
    }
}

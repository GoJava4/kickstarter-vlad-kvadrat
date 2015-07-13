package com.morkva.services;

import com.morkva.entities.Category;
import com.morkva.model.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("categoryService")
@Transactional
public class CategoryService {

    @Autowired
    CategoryDao categoryDAO;

    public List<Category> getAll() {
        return categoryDAO.getAll();
    }

    public Category getById(Integer categoryId) {
        return categoryDAO.getById(categoryId);
    }

    public void delete(Category category) {
        categoryDAO.delete(category);
    }
}

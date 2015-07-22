package com.morkva.services;

import com.morkva.entities.Category;

import java.util.List;

public interface CategoryService {

    void delete(Category category);
    Category getById(Integer id);
    List<Category> getAll();
    void create(Category category);
    void update(Category category);
}

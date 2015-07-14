package com.morkva.model.dao;

import com.morkva.entities.Category;

import java.util.List;

public interface CategoryDao extends Dao<Category> {
    List<Category> getAll();
}

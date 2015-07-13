package com.morkva.model.dao;

import com.morkva.entities.Category;

import java.util.List;

/**
 * Created by koros on 06.07.2015.
 */
public interface CategoryDao extends Dao<Category> {
    List<Category> getAll();
}

package com.morkva.model.dao;

/**
 * Created by koros on 29.06.2015.
 */
public interface Dao<T> {

    void create(T entity);
    T getById(Integer id);
    void update(T entity);
    void delete(T entity);

    void merge(T entity);
}

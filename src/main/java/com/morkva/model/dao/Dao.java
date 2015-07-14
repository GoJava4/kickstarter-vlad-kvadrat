package com.morkva.model.dao;

public interface Dao<T> {

    void create(T entity);
    T getById(Integer id);
    void update(T entity);
    void delete(T entity);

    void merge(T entity);
}

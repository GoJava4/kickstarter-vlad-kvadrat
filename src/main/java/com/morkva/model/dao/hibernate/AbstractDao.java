package com.morkva.model.dao.hibernate;

import com.morkva.model.dao.Dao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractDao<T> implements Dao<T> {

    @Autowired
    protected SessionFactory sessionFactory;

    Class<T> type;

    public AbstractDao(Class<T> type) {
        this.type = type;
    }

    @Override
    public void create(T entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public T getById(Integer id) {
        return (T) sessionFactory.getCurrentSession().get(type, id);
    }

    @Override
    public void update(T entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }
}

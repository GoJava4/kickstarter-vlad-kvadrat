package com.morkva.model.dao.hibernate;

import com.morkva.entities.Category;
import com.morkva.model.dao.CategoryDao;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("categoryDao")
public class CategoryDaoImpl extends AbstractDao<Category> implements CategoryDao {

    public CategoryDaoImpl() {
        super(Category.class);
    }

    @Override
    public List<Category> getAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.createCriteria(Category.class).addOrder(Order.asc("name")).list();
    }
}

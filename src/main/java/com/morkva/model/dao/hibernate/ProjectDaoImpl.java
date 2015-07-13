package com.morkva.model.dao.hibernate;

import com.morkva.entities.Category;
import com.morkva.entities.Project;
import com.morkva.entities.User;
import com.morkva.model.dao.ProjectDao;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("projectDao")
public class ProjectDaoImpl extends AbstractDao<Project> implements ProjectDao {

    public ProjectDaoImpl() {
        super(Project.class);
    }

    @Override
    public List<Project> getProjectsOfCategory(Category category) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Project.class)
                .add(Restrictions.eq("category.id", category.getId()))
                .addOrder(Order.asc("name"))
                .list();
    }

    @Override
    public List<Project> getProjectsOfUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Project.class)
                .add(Restrictions.eq("user.id", user.getId()))
                .addOrder(Order.asc("name"))
                .list();
    }
}

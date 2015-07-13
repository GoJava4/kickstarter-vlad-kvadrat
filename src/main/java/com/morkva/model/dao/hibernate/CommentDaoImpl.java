package com.morkva.model.dao.hibernate;

import com.morkva.entities.Comment;
import com.morkva.entities.Project;
import com.morkva.model.dao.CommentDao;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("commentDao")
public class CommentDaoImpl extends AbstractDao<Comment> implements CommentDao {

    public CommentDaoImpl() {
        super(Comment.class);
    }

    @Override
    public List<Comment> getCommentsOfProject(Project project) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Comment.class)
                .add(Restrictions.eq("project.id", project.getId()))
                .addOrder(Order.asc("date"))
                .list();
    }
}

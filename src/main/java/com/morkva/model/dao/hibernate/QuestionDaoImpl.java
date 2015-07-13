package com.morkva.model.dao.hibernate;

import com.morkva.entities.Project;
import com.morkva.entities.Question;
import com.morkva.model.dao.QuestionDao;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("questionDao")
public class QuestionDaoImpl extends AbstractDao<Question> implements QuestionDao {

    public QuestionDaoImpl() {
        super(Question.class);
    }

    @Override
    public List<Question> getQuestionsOfProject(Project project) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Question.class)
                .add(Restrictions.eq("project.id", project.getId()))
                .addOrder(Order.desc("date"))
                .list();
    }
}

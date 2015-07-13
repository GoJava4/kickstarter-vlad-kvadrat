package com.morkva.model.dao.hibernate;

import com.morkva.entities.Answer;
import com.morkva.entities.Question;
import com.morkva.model.dao.AnswerDao;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("answerDao")
public class AnswerDaoImpl extends AbstractDao<Answer> implements AnswerDao {

    public AnswerDaoImpl() {
        super(Answer.class);
    }

    @Override
    public List<Answer> getAnswersOfQuestion(Question question) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Answer.class)
                .add(Restrictions.eq("question.id", question.getId()))
                .addOrder(Order.desc("date"))
                .list();
    }
}

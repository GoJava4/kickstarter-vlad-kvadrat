package com.morkva.model.dao.hibernate;

import com.morkva.entities.Payment;
import com.morkva.entities.Project;
import com.morkva.entities.User;
import com.morkva.model.dao.PaymentDao;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("paymentDao")
public class PaymentDaoImpl extends AbstractDao<Payment> implements PaymentDao {

    public PaymentDaoImpl() {
        super(Payment.class);
    }

    @Override
    public List<Payment> getPaymentsOfProject(Project project) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Payment.class)
                .add(Restrictions.eq("project.id", project.getId()))
                .addOrder(Order.asc("date"))
                .list();
    }

    @Override
    public List<Payment> getPaymentsOfUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Payment.class)
                .add(Restrictions.eq("user.id", user.getId()))
                .addOrder(Order.asc("date"))
                .list();
    }
}

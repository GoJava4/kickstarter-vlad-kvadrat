package com.morkva.model.dao.hibernate;

import com.morkva.entities.PaymentBonus;
import com.morkva.entities.Project;
import com.morkva.model.dao.PaymentBonusDao;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("paymentBonusDao")
public class PaymentBonusDaoImpl extends AbstractDao<PaymentBonus> implements PaymentBonusDao {

    public PaymentBonusDaoImpl() {
        super(PaymentBonus.class);
    }

    @Override
    public List<PaymentBonus> getPaymentBonusesOfProject(Project project) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(PaymentBonus.class)
                .add(Restrictions.eq("project.id", project.getId()))
                .addOrder(Order.asc("minMoney"))
                .list();
    }
}

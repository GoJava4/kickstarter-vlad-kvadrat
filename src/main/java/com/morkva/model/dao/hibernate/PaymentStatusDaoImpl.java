package com.morkva.model.dao.hibernate;

import com.morkva.entities.PaymentStatus;
import com.morkva.model.dao.PaymentStatusDao;
import org.springframework.stereotype.Repository;

@Repository("paymentStatusDao")
public class PaymentStatusDaoImpl extends AbstractDao<PaymentStatus> implements PaymentStatusDao {

    public PaymentStatusDaoImpl() {
        super(PaymentStatus.class);
    }
}

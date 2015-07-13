package com.morkva.services;

import com.morkva.entities.PaymentStatus;
import com.morkva.model.dao.PaymentStatusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("paymentStatusService")
public class PaymentStatusService {


    @Qualifier("paymentStatusDao")
    @Autowired
    private PaymentStatusDao paymentStatusDao;

    public PaymentStatus getById(Integer id) {
        return paymentStatusDao.getById(id);
    }
}

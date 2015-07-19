package com.morkva.services.impl;

import com.morkva.entities.PaymentStatus;
import com.morkva.model.dao.PaymentStatusDao;
import com.morkva.services.PaymentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("paymentStatusService")
public class PaymentStatusServiceImpl implements PaymentStatusService {

    @Autowired
    private PaymentStatusDao paymentStatusDao;

    @Override
    public PaymentStatus getById(Integer id) {
        return paymentStatusDao.getById(id);
    }
}

package com.morkva.services.impl;

import com.morkva.model.dao.PaymentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("paymentService")
public class PaymentServiceImpl {

    @Autowired
    private PaymentDao paymentDao;
}

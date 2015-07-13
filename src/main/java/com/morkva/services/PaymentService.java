package com.morkva.services;

import com.morkva.model.dao.PaymentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("paymentService")
public class PaymentService {


    @Qualifier("paymentDao")
    @Autowired
    private PaymentDao paymentDao;
}

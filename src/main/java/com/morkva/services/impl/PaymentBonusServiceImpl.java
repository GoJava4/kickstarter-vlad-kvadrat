package com.morkva.services.impl;

import com.morkva.model.dao.PaymentBonusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("paymentBonusService")
public class PaymentBonusServiceImpl {

    @Autowired
    private PaymentBonusDao paymentBonusDao;
}

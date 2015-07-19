package com.morkva.services.impl;

import com.morkva.model.dao.PaymentBonusDao;
import com.morkva.services.PaymentBonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("paymentBonusService")
public class PaymentBonusServiceImpl implements PaymentBonusService {

    @Autowired
    private PaymentBonusDao paymentBonusDao;
}

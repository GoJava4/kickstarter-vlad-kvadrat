package com.morkva.services;

import com.morkva.model.dao.PaymentBonusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("paymentBonusService")
public class PaymentBonusService {

    @Autowired
    private PaymentBonusDao paymentBonusDao;
}

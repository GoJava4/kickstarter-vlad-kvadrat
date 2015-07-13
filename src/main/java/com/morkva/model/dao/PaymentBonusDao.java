package com.morkva.model.dao;

import com.morkva.entities.PaymentBonus;
import com.morkva.entities.Project;

import java.util.List;

public interface PaymentBonusDao extends Dao<PaymentBonus> {

    List<PaymentBonus> getPaymentBonusesOfProject(Project project);
}

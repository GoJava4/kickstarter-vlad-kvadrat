package com.morkva.model.dao;

import com.morkva.entities.Payment;
import com.morkva.entities.Project;
import com.morkva.entities.User;

import java.util.List;

public interface PaymentDao extends Dao<Payment> {

    List<Payment> getPaymentsOfProject(Project project);
    List<Payment> getPaymentsOfUser(User user);
}

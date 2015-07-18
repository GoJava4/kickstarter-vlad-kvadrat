package com.morkva.services.impl;

import com.morkva.entities.*;
import com.morkva.model.dao.PaymentBonusDao;
import com.morkva.model.dao.PaymentDao;
import com.morkva.model.dao.ProjectDao;
import com.morkva.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("projectService")
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDao projectDAO;

    @Autowired
    private PaymentBonusDao paymentBonusDao;

    @Autowired
    private PaymentDao paymentDao;

    @Autowired
    private PaymentStatusServiceImpl paymentStatusService;


    @Override
    public List<Project> getProjectsOfCategory(Category category) {
        return projectDAO.getProjectsOfCategory(category);
    }

    @Override
    public Project getById(Integer projectId) {
        return projectDAO.getById(projectId);
    }

    @Override
    public void update(Project project) {
        projectDAO.update(project);
    }

    /**
     * donate returns updated project that allows us to make one less operation with DB
     *
     * @param id - project ID to donate
     * @param amount - money that donated by user
     * @param user - user that makes donation
     * @return updated Project
     */
    public Project donate(Integer id, Integer amount, User user) {

        Project project = projectDAO.getById(id);

        PaymentStatus okStatus = paymentStatusService.getById(1);

        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setDate(new Date());
        payment.setUser(user);
        payment.setProject(project);
        payment.setStatus(okStatus);
        paymentDao.create(payment);

        List<PaymentBonus> paymentBonusesOfProject = paymentBonusDao.getPaymentBonusesOfProject(project);
        int lastElem = paymentBonusesOfProject.size() - 1;
        for (int i = 0; i < lastElem; i++) {
            if (amount >= paymentBonusesOfProject.get(i).getMinMoney() && amount < paymentBonusesOfProject.get(i + 1).getMinMoney()) {
                updateBonus(paymentBonusesOfProject, i);
            }
        }
        if (amount > paymentBonusesOfProject.get(lastElem).getMinMoney()) {
            updateBonus(paymentBonusesOfProject, lastElem);
        }

        project.setCurrentMoney(project.getCurrentMoney() + amount);
        projectDAO.update(project);
        return project;
    }

    private void updateBonus(List<PaymentBonus> paymentBonusesOfProject, int i) {
        PaymentBonus paymentBonus = paymentBonusesOfProject.get(i);
        paymentBonus.decreaseBonusesLeft();
        paymentBonusDao.update(paymentBonus);
    }
}

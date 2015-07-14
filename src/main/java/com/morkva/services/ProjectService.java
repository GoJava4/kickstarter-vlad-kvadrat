package com.morkva.services;

import com.morkva.entities.*;
import com.morkva.model.dao.PaymentBonusDao;
import com.morkva.model.dao.PaymentDao;
import com.morkva.model.dao.ProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("projectService")
@Transactional
public class ProjectService {

    @Autowired
    ProjectDao projectDAO;

    @Qualifier("paymentBonusDao")
    @Autowired
    private PaymentBonusDao paymentBonusDao;

    @Qualifier("paymentDao")
    @Autowired
    private PaymentDao paymentDao;

    @Autowired
    private PaymentStatusService paymentStatusService;


    public List<Project> getProjectsOfCategory(Category category) {
        return projectDAO.getProjectsOfCategory(category);
    }

    public Project getById(Integer projectId) {
        return projectDAO.getById(projectId);
    }

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

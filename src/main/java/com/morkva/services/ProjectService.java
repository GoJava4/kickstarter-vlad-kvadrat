package com.morkva.services;

import com.morkva.entities.*;
import com.morkva.model.dao.PaymentBonusDao;
import com.morkva.model.dao.PaymentDao;
import com.morkva.model.dao.ProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("projectService")
@Transactional
public class ProjectService {

    @Autowired
    private ProjectDao projectDAO;

    @Autowired
    private PaymentBonusDao paymentBonusDao;

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
     * donate adds successful payment, updates project, decreases bonuses if needed and
     * returns updated project that allows us to make one less operation with DB
     *
     * @param id - project ID to donate
     * @param amount - money that donated by user
     * @param user - user that makes donation
     * @return updated Project
     */
    public Project donate(Integer id, Double amount, User user) {
        Project project = projectDAO.getById(id);
        //creating successful payment
        createPayment(amount, user, project);
        //checking if payment enough for exact bonus
        //and if bonuses still left, decreases that bonus amount and updates it in DB
        checkForBonusForPayment(amount, project);

        //Updating the project
        project.setCurrentMoney(project.getCurrentMoney() + amount.intValue());
        projectDAO.update(project);

        return project;
    }

    private void checkForBonusForPayment(Double amount, Project project) {
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
    }

    private void createPayment(Double amount, User user, Project project) {
        PaymentStatus okStatus = paymentStatusService.getById(1);

        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setDate(new Date());
        payment.setUser(user);
        payment.setProject(project);
        payment.setStatus(okStatus);
        paymentDao.create(payment);
    }

    private void updateBonus(List<PaymentBonus> paymentBonusesOfProject, int i) {
        PaymentBonus paymentBonus = paymentBonusesOfProject.get(i);
        if (paymentBonus.getBonusesLeft() > 0) {
            paymentBonus.decreaseBonusesLeft();
            paymentBonusDao.update(paymentBonus);
        }
    }
}

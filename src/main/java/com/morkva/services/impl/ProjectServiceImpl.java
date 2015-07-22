package com.morkva.services.impl;

import com.morkva.entities.*;
import com.morkva.model.dao.PaymentBonusDao;
import com.morkva.model.dao.PaymentDao;
import com.morkva.model.dao.ProjectDao;
import com.morkva.services.PaymentStatusService;
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
    private PaymentStatusService paymentStatusService;

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
    @Override
    public Project donate(Integer id, Double amount, User user) {
        Project project = projectDAO.getById(id);
        //checking if payment enough for exact bonus
        //and if bonuses still left, decreases that bonus amount and updates it in DB
        PaymentBonus paymentBonus = checkForBonusForPayment(amount, project);
        //creating successful payment
        createPayment(amount, user, project, paymentBonus);
        if (paymentBonus != null) {
            updateBonus(paymentBonus);
        }

        //Updating the project
        project.setCurrentMoney(project.getCurrentMoney() + amount.intValue());
        projectDAO.update(project);

        return project;
    }

    @Override
    public void create(Project projectToAdd) {
        projectDAO.create(projectToAdd);
    }

    @Override
    public void simpleCreate(Project project) {
        Project result = new Project.Builder()
                .setShortDescr(project.getShortDescr())
                .setCategory(project.getCategory())
                .setNeedMoney(project.getNeedMoney())
                .setEndingDate(project.getEndingDate())
                .setFullDescription(project.getFullDescription())
                .setUser(project.getUser())
                .setName(project.getName())
                .setCurrentMoney(0)
                .setAddingDate(new Date())
                .setSuccessfullyFinished(false)
                .build();
        create(result);
    }

    @Override
    public void delete(Project project) {
        projectDAO.delete(project);
    }

    @Override
    public List<Project> getProjectsOf(User user) {
        return projectDAO.getProjectsOfUser(user);
    }

    @Override
    public List<Project> getProjectsOf(Category category) {
        return projectDAO.getProjectsOfCategory(category);
    }

    @Override
    public List<Project> getAllFinishedProjects() {
        return projectDAO.getAllFinishedProjects();
    }

    @Override
    public List<Project> getFinishedProjectsOf(Category category) {
        return projectDAO.getFinishedProjectsOf(category);
    }

    @Override
    public List<Project> getFinishedProjectsOf(User user) {
        return projectDAO.getFinishedProjectsOf(user);
    }

    @Override
    public List<Project> getNotFinishedProjectsOf(Category category) {
        return projectDAO.getNotFinishedProjectsOf(category);
    }

    @Override
    public List<Project> getNotFinishedProjectsOf(User user) {
        return projectDAO.getNotFinishedProjectsOf(user);
    }

    private PaymentBonus checkForBonusForPayment(Double amount, Project project) {
        List<PaymentBonus> paymentBonusesOfProject = paymentBonusDao.getPaymentBonusesOfProject(project);
        int lastElem = paymentBonusesOfProject.size() - 1;
        for (int i = 0; i < lastElem; i++) {
            if (amount >= paymentBonusesOfProject.get(i).getMinMoney() && amount < paymentBonusesOfProject.get(i + 1).getMinMoney()) {
                return checkForBonusesLeft(paymentBonusesOfProject.get(i));
            }
        }
        if (amount > paymentBonusesOfProject.get(lastElem).getMinMoney()) {
            return checkForBonusesLeft(paymentBonusesOfProject.get(lastElem));
        }
        return null;
    }

    private PaymentBonus checkForBonusesLeft(PaymentBonus paymentBonus) {
        if (paymentBonus.getBonusesLeft() > 0) {
            return paymentBonus;
        } else {
            return null;
        }
    }



    private void createPayment(Double amount, User user, Project project, PaymentBonus paymentBonus) {
        PaymentStatus okStatus = paymentStatusService.getById(1);

        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setDate(new Date());
        payment.setUser(user);
        payment.setProject(project);
        payment.setStatus(okStatus);
        payment.setPaymentBonus(paymentBonus);
        paymentDao.create(payment);
    }

    private void updateBonus(PaymentBonus paymentBonus) {
        if (paymentBonus.getBonusesLeft() > 0) {
            paymentBonus.decreaseBonusesLeft();
            paymentBonusDao.update(paymentBonus);
        }
    }
}

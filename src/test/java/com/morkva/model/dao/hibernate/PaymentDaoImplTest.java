package com.morkva.model.dao.hibernate;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.morkva.entities.Payment;
import com.morkva.entities.Project;
import com.morkva.entities.User;
import com.morkva.model.dao.PaymentDao;
import com.morkva.model.dao.PaymentStatusDao;
import com.morkva.model.dao.ProjectDao;
import com.morkva.model.dao.UserDao;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context-test.xml"})
@Transactional
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
@DatabaseSetup(value = "classpath:sampleData.xml", type = DatabaseOperation.CLEAN_INSERT)
public class PaymentDaoImplTest {

    @Autowired
    PaymentDao paymentDao;

    @Autowired
    ProjectDao projectDao;

    @Autowired
    UserDao userDao;

    @Autowired
    PaymentStatusDao paymentStatusDao;

    @Test
    public void testGetPaymentsOfProject() throws Exception {
        Project project = projectDao.getById(1);
        List<Payment> paymentsOfProject = paymentDao.getPaymentsOfProject(project);
        Assert.assertEquals(1,paymentsOfProject.size());
    }

    @Test
    public void testGetPaymentsOfUser() throws Exception {
        User user = userDao.getById(1);
        List<Payment> paymentsOfUser = paymentDao.getPaymentsOfUser(user);
        Assert.assertEquals(1,paymentsOfUser.size());
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:paymentTest/expectedCreateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "payments"
    )
    public void testCreate() throws Exception {
        Payment payment = new Payment();
        payment.setId(4);
        payment.setAmount(500);
        payment.setProject(projectDao.getById(2));
        payment.setStatus(paymentStatusDao.getById(1));
        payment.setUser(userDao.getById(3));

        paymentDao.create(payment);
    }

    @Test
    public void testGetById() throws Exception {
        Payment payment = paymentDao.getById(1);
        Assert.assertEquals(10., payment.getAmount());
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:paymentTest/expectedUpdateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "payments"
    )
    public void testUpdate() throws Exception {
        Payment payment = paymentDao.getById(1);
        payment.setAmount(100.);
        paymentDao.update(payment);
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:paymentTest/expectedDeleteData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "payments"
    )
    public void testDelete() throws Exception {
        Payment payment = paymentDao.getById(3);
        paymentDao.delete(payment);
    }
}
package com.morkva.model.dao.hibernate;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.morkva.entities.Payment;
import com.morkva.entities.PaymentStatus;
import com.morkva.model.dao.PaymentStatusDao;
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
public class PaymentStatusDaoImplTest {

    @Autowired
    PaymentStatusDao paymentStatusDao;

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:paymentStatusTest/expectedCreateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "payment_statuses"
    )
    public void testCreate() throws Exception {
        PaymentStatus paymentStatus = new PaymentStatus();
        paymentStatus.setId(5);
        paymentStatus.setStatus("New payment_status");
        paymentStatusDao.create(paymentStatus);
    }

    @Test
    public void testGetById() throws Exception {
        PaymentStatus paymentStatus = paymentStatusDao.getById(1);
        Assert.assertEquals("payment_status 1", paymentStatus.getStatus());
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:paymentStatusTest/expectedUpdateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "payment_statuses"
    )
    public void testUpdate() throws Exception {
        PaymentStatus paymentStatus = paymentStatusDao.getById(1);
        paymentStatus.setStatus("Updated Status");
        paymentStatusDao.update(paymentStatus);
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:paymentStatusTest/expectedDeleteData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "payment_statuses"
    )
    public void testDelete() throws Exception {
        PaymentStatus paymentStatus = paymentStatusDao.getById(4);
        paymentStatusDao.delete(paymentStatus);
    }
}
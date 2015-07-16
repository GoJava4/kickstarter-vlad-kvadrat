package com.morkva.model.dao.hibernate;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.morkva.entities.FullDescription;
import com.morkva.entities.PaymentBonus;
import com.morkva.entities.Project;
import com.morkva.model.dao.PaymentBonusDao;
import com.morkva.model.dao.ProjectDao;
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
public class PaymentBonusDaoImplTest {

    @Autowired
    PaymentBonusDao paymentBonusDao;

    @Test
    public void testGetPaymentBonusesOfProject() throws Exception {
        Project project = new Project.Builder()
                .setId(1)
                .build();

        List <PaymentBonus> paymentBonuses = paymentBonusDao.getPaymentBonusesOfProject(project);
        Assert.assertEquals(2, paymentBonuses.size());
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:paymentBonusesTest/expectedCreateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "payment_bonuses"
    )
    public void testCreate() throws Exception {
        Project project = new Project.Builder()
                .setId(1)
                .build();

        FullDescription fullDescription = new FullDescription();
        fullDescription.setId(6);

        PaymentBonus paymentBonus = new PaymentBonus();
        paymentBonus.setId(3);
        paymentBonus.setFullDescription(fullDescription);
        paymentBonus.setBonusesLeft(10);
        paymentBonus.setMinMoney(300);
        paymentBonus.setProject(project);

        paymentBonusDao.create(paymentBonus);
    }

    @Test
    public void testGetById() throws Exception {
        PaymentBonus paymentBonus = paymentBonusDao.getById(1);
        Assert.assertEquals(100L, (long)paymentBonus.getMinMoney());
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:paymentBonusesTest/expectedUpdateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "payment_bonuses"
    )
    public void testUpdate() throws Exception {
        PaymentBonus paymentBonus = paymentBonusDao.getById(2);
        paymentBonus.setMinMoney(250);
        paymentBonusDao.update(paymentBonus);
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:paymentBonusesTest/expectedDeleteData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "payment_bonuses"
    )
    public void testDelete() throws Exception {
        PaymentBonus paymentBonus = paymentBonusDao.getById(2);
        paymentBonusDao.delete(paymentBonus);
    }
}
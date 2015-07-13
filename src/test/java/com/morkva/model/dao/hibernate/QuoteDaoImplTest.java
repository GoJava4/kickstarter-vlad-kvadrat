package com.morkva.model.dao.hibernate;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.morkva.entities.Quote;
import com.morkva.model.dao.QuoteDao;
import org.junit.Assert;
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

/**
 * Created by koros on 05.07.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context-test.xml"})
@Transactional
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
@DatabaseSetup(value = "classpath:sampleData.xml", type = DatabaseOperation.CLEAN_INSERT)
public class QuoteDaoImplTest {

    @Autowired
    QuoteDao quoteDao;

    @Test
    public void testGetRandom() throws Exception {
        Quote random = quoteDao.getRandom();
        Assert.assertNotNull(random);
    }


    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:quoteTest/expectedCreateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "quotes"
    )
    public void testCreate() throws Exception {
        Quote quote = new Quote("New Value", "New Author");
        quoteDao.create(quote);
    }

    @Test
    public void testGetById() throws Exception {
        Quote byId = quoteDao.getById(1);
        Assert.assertNotNull(byId);
        Assert.assertEquals("value 1", byId.getValue());
        Assert.assertEquals("author 1", byId.getAuthor());
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:quoteTest/expectedUpdateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "quotes"
    )
    public void testUpdate() throws Exception {
        Quote quoteToUpdate = quoteDao.getById(3);
        quoteToUpdate.setValue("Updated Value");
        quoteToUpdate.setAuthor("Updated Author");
        quoteDao.update(quoteToUpdate);
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:quoteTest/expectedDeleteData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "quotes"
    )
    public void testDelete() throws Exception {
        Quote quoteToDelete = quoteDao.getById(3);
        quoteDao.delete(quoteToDelete);
    }
}
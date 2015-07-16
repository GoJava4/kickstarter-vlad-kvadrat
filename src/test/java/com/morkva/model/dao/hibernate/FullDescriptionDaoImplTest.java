package com.morkva.model.dao.hibernate;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.morkva.entities.FullDescription;
import com.morkva.model.dao.FullDescriptionDao;
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
public class FullDescriptionDaoImplTest {

    @Autowired
    FullDescriptionDao fullDescriptionDao;

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:fullDescriprionTest/expectedCreateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "full_descriptions"
    )
    public void testCreate() throws Exception {
        FullDescription fullDescription = new FullDescription();
        fullDescription.setValue("New full_description");
        fullDescriptionDao.create(fullDescription);
    }

    @Test
    public void testGetById() throws Exception {
        FullDescription fullDescription = fullDescriptionDao.getById(1);
        Assert.assertEquals("full_description 1", fullDescription.getValue());
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:fullDescriprionTest/expectedUpdateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "full_descriptions"
    )
    public void testUpdate() throws Exception {
        FullDescription fullDescription = fullDescriptionDao.getById(1);
        fullDescription.setValue("Updated full_description 1");
        fullDescriptionDao.update(fullDescription);
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:fullDescriprionTest/expectedDeleteData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "full_descriptions"
    )
    public void testDelete() throws Exception {
        FullDescription fullDescription = fullDescriptionDao.getById(8);
        fullDescriptionDao.delete(fullDescription);
    }
}
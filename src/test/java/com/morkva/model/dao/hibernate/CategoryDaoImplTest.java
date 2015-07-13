package com.morkva.model.dao.hibernate;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.morkva.entities.Category;
import com.morkva.model.dao.CategoryDao;
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

import java.util.List;

/**
 * Created by koros on 06.07.2015.
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
public class CategoryDaoImplTest {

    @Autowired
    CategoryDao categoryDao;

    @Test
    public void testGetAll() throws Exception {
        List<Category> all = categoryDao.getAll();
        Assert.assertTrue(all.size() > 1);
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:categoryTest/expectedCreateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "categories"
    )
    public void testCreate() throws Exception {
        Category category = new Category("New Name");
        categoryDao.create(category);
    }

    @Test
    public void testGetById() throws Exception {
        Category byId = categoryDao.getById(1);
        Assert.assertNotNull(byId);
        Assert.assertEquals("name 1", byId.getName());
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:categoryTest/expectedUpdateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "categories"
    )
    public void testUpdate() throws Exception {
        Category byId = categoryDao.getById(3);
        byId.setName("Updated Name");
        categoryDao.update(byId);
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:categoryTest/expectedDeleteData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "categories"
    )
    public void testDelete() throws Exception {
        Category byId = categoryDao.getById(3);
        categoryDao.delete(byId);
    }
}
package com.morkva.model.dao.hibernate;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.morkva.entities.UserRole;
import com.morkva.model.dao.UserRoleDao;
import junit.framework.Assert;
import org.junit.Ignore;
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
public class UserRoleDaoImplTest {

    @Autowired
    UserRoleDao userRoleDao;

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:userRoleTest/expectedCreateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "user_roles"
    )
    public void testCreate() throws Exception {
        UserRole userRole = new UserRole("New role_name");
        userRoleDao.create(userRole);
    }

    @Test
    public void testGetById() throws Exception {
        UserRole userRole = userRoleDao.getById(1);
        Assert.assertEquals("role_name 1", userRole.getName());
    }

    @Test
    @Ignore
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:userRoleTest/expectedUpdateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "user_roles"
    )
    public void testUpdate() throws Exception {
        String oldUserRoleName = userRoleDao.getById(3).getName();
        UserRole userRole = new UserRole("Updated " + oldUserRoleName);
        userRoleDao.update(userRole);
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:userRoleTest/expectedDeleteData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "user_roles"
    )
    public void testDelete() throws Exception {
        UserRole userRole = userRoleDao.getById(3);
        userRoleDao.delete(userRole);
    }
}
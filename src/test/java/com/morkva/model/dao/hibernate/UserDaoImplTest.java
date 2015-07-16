package com.morkva.model.dao.hibernate;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.morkva.entities.User;
import com.morkva.model.dao.UserDao;
import com.morkva.model.dao.UserRoleDao;
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
public class UserDaoImplTest {

    @Autowired
    UserDao userDao;

    @Autowired
    UserRoleDao userRoleDao;

    @Test
    public void testGetByLogin() throws Exception {
        String login = "login 1";
        User user = userDao.getByLogin(login);
        Assert.assertEquals("username 1", user.getUsername());
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:userTest/expectedActivateUserData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "users"
    )
    public void testActivateUser() throws Exception {
        Integer userId = 4;
        userDao.activateUser(userId);
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:userTest/expectedDeactivateUserData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "users"
    )
    public void testDeactivateUser() throws Exception {
        Integer userId = 3;
        userDao.deactivateUser(userId);
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:userTest/expectedCreateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "users"
    )
    public void testCreate() throws Exception {
        User user = new User();
        user.setId(5);
        user.setLogin("New login");
        user.setPassword("password 5");
        user.setEmail("email 5");
        user.setPersonalInfo("personal_info 5");
        user.setRole(userRoleDao.getById(1));
        user.setUsername("username 5");
        user.setActive(true);

        userDao.create(user);
    }

    @Test
    public void testGetById() throws Exception {
        User user = userDao.getById(1);
        Assert.assertEquals("login 1", user.getLogin());
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:userTest/expectedUpdateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "users"
    )
    public void testUpdate() throws Exception {
        User user = userDao.getById(3);
        user.setPersonalInfo("Updated " + user.getPersonalInfo());
        userDao.update(user);
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:userTest/expectedDeleteData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "users"
    )
    public void testDelete() throws Exception {
        User user = userDao.getById(4);
        userDao.delete(user);
    }
}
package com.morkva.model.dao.hibernate;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.morkva.entities.Comment;
import com.morkva.entities.Project;
import com.morkva.entities.User;
import com.morkva.model.dao.CommentDao;
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
public class CommentDaoImplTest {

    @Autowired
    CommentDao commentDao;

    @Autowired
    ProjectDao projectDao;

    @Autowired
    UserDao userDao;

    @Test
    public void testGetCommentsOfProject() throws Exception {
        Project project = projectDao.getById(1);
        List <Comment> commentsOfProject = commentDao.getCommentsOfProject(project);
        Assert.assertEquals(3, commentsOfProject.size());
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:commentTest/expectedCreateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "comments"
    )
    public void testCreate() throws Exception {
        Comment comment = new Comment();
        comment.setComment("New Comment");
        commentDao.create(comment);
    }

    @Test
    public void testGetById() throws Exception {
        Comment comment = commentDao.getById(1);
        Assert.assertEquals("comment 1", comment.getComment());
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:commentTest/expectedUpdateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "comments"
    )
    public void testUpdate() throws Exception {
        Comment comment = commentDao.getById(4);
        comment.setComment("Updated Comment");
        commentDao.update(comment);
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:commentTest/expectedDeleteData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "comments"
    )
    public void testDelete() throws Exception {
        Comment comment = commentDao.getById(4);
        commentDao.delete(comment);
    }

    @Test
    public void testGetCommentsOfUser() throws Exception {
        User user = userDao.getById(2);
        List <Comment> commentsOfProject = commentDao.getCommentsOfUser(user);
        Assert.assertEquals(1, commentsOfProject.size());
    }
}
package com.morkva.model.dao.hibernate;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.morkva.entities.Project;
import com.morkva.entities.Question;
import com.morkva.model.dao.ProjectDao;
import com.morkva.model.dao.QuestionDao;
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
public class QuestionDaoImplTest {

    @Autowired
    QuestionDao questionDao;

    @Autowired
    UserDao userDao;

    @Autowired
    ProjectDao projectDao;

    @Test
    public void testGetQuestionsOfProject() throws Exception {
        Project project = projectDao.getById(1);
        List<Question> questionList = questionDao.getQuestionsOfProject(project);
        Assert.assertEquals(2,questionList.size());
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:questionTest/expectedCreateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "questions"
    )
    public void testCreate() throws Exception {
        Question question = new Question();
        question.setUser(userDao.getById(3));
        question.setProject(projectDao.getById(1));
        question.setQuestion("New Question");

        questionDao.create(question);
    }

    @Test
    public void testGetById() throws Exception {
        Question question = questionDao.getById(1);
        Assert.assertEquals("question 1", question.getQuestion());
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:questionTest/expectedUpdateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "questions"
    )
    public void testUpdate() throws Exception {
        Question question = questionDao.getById(3);
        question.setQuestion("Updated "+question.getQuestion());
        questionDao.update(question);
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:questionTest/expectedDeleteData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "questions"
    )
    public void testDelete() throws Exception {
        Question question = questionDao.getById(3);
        questionDao.delete(question);
    }
}
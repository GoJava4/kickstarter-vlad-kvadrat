package com.morkva.model.dao.hibernate;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.morkva.entities.Answer;
import com.morkva.entities.Question;
import com.morkva.model.dao.AnswerDao;
import com.morkva.model.dao.QuestionDao;
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
public class AnswerDaoImplTest {

    @Autowired
    AnswerDao answerDao;

    @Autowired
    QuestionDao questionDao;

    @Test
    public void testGetAnswersOfQuestion() throws Exception {
        Question question = questionDao.getById(1);
        Assert.assertNotNull(answerDao.getAnswersOfQuestion(question));
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:answerTest/expectedCreateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "answers"
    )
    public void testCreate() throws Exception {
        Answer answer = new Answer();
        answer.setAnswer("New Answer");
        answerDao.create(answer);
    }

    @Test
    public void testGetById() throws Exception {
        Answer answer = answerDao.getById(1);
        Assert.assertEquals("answer 1", answer.getAnswer());
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:answerTest/expectedUpdateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "answers"
    )
    public void testUpdate() throws Exception {
        Answer answer = answerDao.getById(2);
        answer.setAnswer("Updated Answer");
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:answerTest/expectedDeleteData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "answers"
    )
    public void testDelete() throws Exception {
        Answer answer = answerDao.getById(2);
        answerDao.delete(answer);
    }
}
package com.morkva.services.impl;

import com.morkva.entities.Answer;
import com.morkva.entities.Question;
import com.morkva.model.dao.AnswerDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;

public class AnswerServiceImplTest {

    @Mock
    private AnswerDao answerDao;

    @InjectMocks
    private AnswerServiceImpl answerService;

    @Before
    public void init () {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAnswersOfQuestion() throws Exception {
        Question question = Mockito.mock(Question.class);
        List<Answer> answerList = new LinkedList<>();
        answerList.add(new Answer());
        answerList.add(new Answer());
        answerList.add(new Answer());
        Mockito.when(answerDao.getAnswersOfQuestion(question)).thenReturn(answerList);
        Assert.assertEquals(answerList, answerService.getAnswersOfQuestion(question));
    }
}
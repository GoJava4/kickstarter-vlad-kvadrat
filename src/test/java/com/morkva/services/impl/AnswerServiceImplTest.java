package com.morkva.services.impl;

import com.morkva.entities.Answer;
import com.morkva.entities.Question;
import com.morkva.model.dao.AnswerDao;
import com.morkva.services.AnswerService;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        Question question = mock(Question.class);
        List<Answer> answerList = Arrays.asList(new Answer(), new Answer(), new Answer());
        when(answerDao.getAnswersOfQuestion(question)).thenReturn(answerList);
        Assert.assertEquals(answerList, answerService.getAnswersOfQuestion(question));
    }
}
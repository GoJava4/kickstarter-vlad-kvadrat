package com.morkva.services.impl;

import com.morkva.entities.Project;
import com.morkva.entities.Question;
import com.morkva.model.dao.QuestionDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuestionServiceImplTest {

    @Mock
    private QuestionDao questionDao;

    @InjectMocks
    private QuestionServiceImpl questionService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetQuestionsOfProject() throws Exception {
        Project project = mock(Project.class);
        Question question = mock(Question.class);
        List<Question> questions = new LinkedList<>();
        questions.add(question);
        when(questionDao.getQuestionsOfProject(project)).thenReturn(questions);
        Assert.assertEquals(questions, questionService.getQuestionsOfProject(project));
    }
}
package com.morkva.services.impl;

import com.morkva.entities.Project;
import com.morkva.entities.Question;
import com.morkva.model.dao.QuestionDao;
import com.morkva.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("questionService")
@Transactional
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionDao questionDao;

    @Override
    public List<Question> getQuestionsOfProject(Project project) {
        return questionDao.getQuestionsOfProject(project);
    }

}

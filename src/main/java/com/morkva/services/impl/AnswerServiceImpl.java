package com.morkva.services.impl;

import com.morkva.entities.Answer;
import com.morkva.entities.Question;
import com.morkva.model.dao.AnswerDao;
import com.morkva.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("answerService")
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerDao answerDao;

    @Override
    public List<Answer> getAnswersOfQuestion(Question question) {
        return answerDao.getAnswersOfQuestion(question);
    }
}

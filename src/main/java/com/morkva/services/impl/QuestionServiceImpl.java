package com.morkva.services.impl;

import com.morkva.model.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("questionService")
public class QuestionServiceImpl {

    @Autowired
    private QuestionDao questionDao;

}

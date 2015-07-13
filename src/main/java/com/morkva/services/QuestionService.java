package com.morkva.services;

import com.morkva.model.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("questionService")
public class QuestionService {


    @Qualifier("questionDao")
    @Autowired
    private QuestionDao questionDao;


}

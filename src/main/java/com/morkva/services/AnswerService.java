package com.morkva.services;

import com.morkva.model.dao.AnswerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("answerService")
public class AnswerService {


    @Qualifier("answerDao")
    @Autowired
    private AnswerDao answerDao;
}

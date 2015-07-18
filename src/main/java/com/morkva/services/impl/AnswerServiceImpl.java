package com.morkva.services.impl;

import com.morkva.model.dao.AnswerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("answerService")
public class AnswerServiceImpl {

    @Autowired
    private AnswerDao answerDao;
}

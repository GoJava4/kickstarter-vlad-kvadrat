package com.morkva.model.dao;

import com.morkva.entities.Answer;
import com.morkva.entities.Question;

import java.util.List;

public interface AnswerDao extends Dao<Answer> {
    List<Answer> getAnswersOfQuestion(Question question);
}

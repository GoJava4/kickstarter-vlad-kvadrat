package com.morkva.services;

import com.morkva.entities.Answer;
import com.morkva.entities.Question;

import java.util.List;

public interface AnswerService {
    List<Answer> getAnswersOfQuestion(Question question);
}

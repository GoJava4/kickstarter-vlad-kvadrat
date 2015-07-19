package com.morkva.services;

import com.morkva.entities.Project;
import com.morkva.entities.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestionsOfProject(Project project);
}

package com.morkva.model.dao;

import com.morkva.entities.Project;
import com.morkva.entities.Question;

import java.util.List;

public interface QuestionDao extends Dao<Question> {
    List<Question> getQuestionsOfProject(Project project);
}

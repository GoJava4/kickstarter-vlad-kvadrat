package com.morkva.services;

import com.morkva.entities.Category;
import com.morkva.entities.Project;
import com.morkva.entities.User;

import java.util.List;

public interface ProjectService {
    List<Project> getProjectsOfCategory(Category category);

    Project getById(Integer projectId);

    void update(Project project);

    Project donate(Integer id, Double amount, User user);
}

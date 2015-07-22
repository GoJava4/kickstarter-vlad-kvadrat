package com.morkva.services;

import com.morkva.entities.Category;
import com.morkva.entities.Project;
import com.morkva.entities.User;

import java.util.List;

public interface ProjectService {
    @Deprecated
    List<Project> getProjectsOfCategory(Category category);

    Project getById(Integer projectId);

    void update(Project project);

    Project donate(Integer id, Double amount, User user);

    void create(Project projectToAdd);

    void simpleCreate(Project project);

    void delete(Project project);

    List<Project> getProjectsOf(User user);

    List<Project> getProjectsOf(Category category);

    List<Project> getAllFinishedProjects();

    List<Project> getFinishedProjectsOf(Category category);

    List<Project> getFinishedProjectsOf(User user);

    List<Project> getNotFinishedProjectsOf(Category category);

    List<Project> getNotFinishedProjectsOf(User user);
}

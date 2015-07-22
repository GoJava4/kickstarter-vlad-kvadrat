package com.morkva.model.dao;

import com.morkva.entities.Category;
import com.morkva.entities.Project;
import com.morkva.entities.User;

import java.util.List;

public interface ProjectDao extends Dao<Project> {

    List<Project> getProjectsOfUser(User user);

    List<Project> getProjectsOfCategory(Category category);

    List<Project> getAllFinishedProjects();

    List<Project> getFinishedProjectsOf(Category category);

    List<Project> getFinishedProjectsOf(User user);

    List<Project> getNotFinishedProjectsOf(Category category);

    List<Project> getNotFinishedProjectsOf(User user);
}

package com.morkva.model.dao;

import com.morkva.entities.Category;
import com.morkva.entities.Project;
import com.morkva.entities.User;

import java.util.List;

public interface ProjectDao extends Dao<Project> {

    List<Project> getProjectsOfCategory(Category category);
    List<Project> getProjectsOfUser(User user);

}

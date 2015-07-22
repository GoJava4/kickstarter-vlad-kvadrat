package com.morkva.model.dao;

import com.morkva.entities.Comment;
import com.morkva.entities.Project;
import com.morkva.entities.User;

import java.util.List;

public interface CommentDao extends Dao<Comment> {
    List<Comment> getCommentsOfProject(Project project);

    List<Comment> getCommentsOfUser(User user);
}

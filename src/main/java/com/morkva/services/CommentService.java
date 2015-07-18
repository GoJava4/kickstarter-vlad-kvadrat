package com.morkva.services;

import com.morkva.entities.Comment;
import com.morkva.entities.Project;
import com.morkva.entities.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface CommentService {

    void create(Project project, User userByLogin, Date date, String commentValue);

    List<Comment> getCommentsOfProject(Project project);
}

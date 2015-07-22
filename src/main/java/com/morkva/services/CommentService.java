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

    Comment getById(Integer id);

    void create(Comment comment);

    void update(Comment comment);

    void delete(Comment comment);

    List<Comment> getCommentsOf(User user);

    List<Comment> getCommentsOf(Project project);

}

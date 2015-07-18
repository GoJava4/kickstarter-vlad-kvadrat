package com.morkva.services.impl;

import com.morkva.entities.Comment;
import com.morkva.entities.Project;
import com.morkva.entities.User;
import com.morkva.model.dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("commentService")
public class CommentServiceImpl {

    @Autowired
    private CommentDao commentDao;


    @Transactional
    public void create(Project project, User userByLogin, Date date, String commentValue) {
        Comment comment = new Comment();
        comment.setProject(project);
        comment.setUser(userByLogin);
        comment.setDate(date);
        comment.setComment(commentValue);

        commentDao.create(comment);
    }

    @Transactional
    public List<Comment> getCommentsOfProject(Project project) {
        return commentDao.getCommentsOfProject(project);
    }
}

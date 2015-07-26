package com.morkva.services.impl;

import com.morkva.entities.Comment;
import com.morkva.entities.Project;
import com.morkva.entities.User;
import com.morkva.model.dao.CommentDao;
import com.morkva.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public void create(Project project, User userByLogin, Date date, String commentValue) {
        Comment comment = new Comment();
        comment.setProject(project);
        comment.setUser(userByLogin);
        comment.setDate(date);
        comment.setComment(commentValue);

        commentDao.create(comment);
    }

    @Override
    public Comment getById(Integer id) {
        return commentDao.getById(id);
    }

    @Override
    public void update(Comment comment) {
        commentDao.update(comment);
    }

    @Override
    public void delete(Comment comment) {
        commentDao.delete(comment);
    }

    @Override
    public List<Comment> getCommentsOf(User user) {
        return commentDao.getCommentsOfUser(user);
    }

    @Override
    public List<Comment> getCommentsOf(Project project) {
        return commentDao.getCommentsOfProject(project);
    }

    @Override
    public void create(Comment comment) {
        comment.setDate(new Date());
        commentDao.create(comment);
    }
}

package com.morkva.services;

import com.morkva.entities.Comment;
import com.morkva.entities.Project;
import com.morkva.entities.User;
import com.morkva.model.dao.CommentDao;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class CommentServiceTest {

    @Mock
    CommentDao commentDao;

    @InjectMocks
    CommentService commentService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    User user;

    @Mock
    Project project;

    @Mock
    Date date;

    @Ignore
    @Test
    public void testCreate() throws Exception {
        String text = "Mocked Comment";
        Comment comment = new Comment();
        comment.setProject(project);
        comment.setUser(user);
        comment.setDate(date);
        comment.setComment(text);
        commentService.create(project, user, date, text);
        verify(commentDao).create(comment);
    }

    @Test
    public void testGetCommentsOfProject() throws Exception {

    }
}
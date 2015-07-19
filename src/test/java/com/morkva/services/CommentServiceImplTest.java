package com.morkva.services;

import com.morkva.entities.Comment;
import com.morkva.entities.Project;
import com.morkva.entities.User;
import com.morkva.model.dao.CommentDao;
import com.morkva.services.impl.CommentServiceImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CommentServiceImplTest {

    @Mock
    private CommentDao commentDao;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    private User user;

    @Mock
    private Project project;

    @Mock
    private Date date;

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
        List<Comment> listOfComments = Arrays.asList(new Comment(), new Comment(), new Comment());
        when(commentDao.getCommentsOfProject(project)).thenReturn(listOfComments);
        assertSame(listOfComments, commentService.getCommentsOfProject(project));
    }
}
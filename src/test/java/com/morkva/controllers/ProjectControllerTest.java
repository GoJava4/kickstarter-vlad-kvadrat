package com.morkva.controllers;

import com.morkva.entities.Project;
import com.morkva.entities.User;
import com.morkva.services.CommentService;
import com.morkva.services.ProjectService;
import com.morkva.services.UserDetailsServiceExtended;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;

public class ProjectControllerTest {

    @Mock
    ProjectService projectService;

    @Mock
    UserDetailsServiceExtended userDetailsService;

    @Mock
    CommentService commentService;

    @InjectMocks
    ProjectController projectController;

    MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
    }

    @Test
    public void testShowProject() throws Exception {

    }

    @Test
    public void testDonateToProject() throws Exception {

    }

    @Ignore
    @Test
    public void testAddComment() throws Exception {
        doThrow(NullPointerException.class).when(commentService).create(any(Project.class), any(User.class), any(Date.class), "Comment");
        //TODO I don't know how to test it
    }
}
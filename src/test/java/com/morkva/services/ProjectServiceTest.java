package com.morkva.services;

import com.morkva.entities.Category;
import com.morkva.entities.Project;
import com.morkva.model.dao.ProjectDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by koros on 30.06.2015.
 */
public class ProjectServiceTest {

    @Mock
    ProjectDao projectDAO;

    @InjectMocks
    ProjectService projectService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetById() {
        Project project = new Project();
        Mockito.when(projectDAO.getById(1)).thenReturn(project);
        Assert.assertEquals(project, projectService.getById(1));
    }


    @Test
    public void testGetProjectsOfCategory() {
        Category category = new Category();
        Mockito.when(projectDAO.getProjectsOfCategory(category)).thenReturn(new LinkedList<>(Collections.singletonList(new Project())));
        Assert.assertTrue(projectService.getProjectsOfCategory(category).size() == 1);
    }

    @Test
    public void testUpdate() {
        Project project = new Project();
        projectService.update(project);
        Mockito.verify(projectDAO).update(project);
    }
}

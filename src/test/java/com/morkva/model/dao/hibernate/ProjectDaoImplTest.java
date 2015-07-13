package com.morkva.model.dao.hibernate;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.morkva.entities.Category;
import com.morkva.entities.Project;
import com.morkva.model.dao.CategoryDao;
import com.morkva.model.dao.ProjectDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by koros on 06.07.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context-test.xml"})
@Transactional
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
@DatabaseSetup(value = "classpath:sampleData.xml", type = DatabaseOperation.CLEAN_INSERT)
public class ProjectDaoImplTest {

    @Autowired
    ProjectDao projectDao;

    @Autowired
    CategoryDao categoryDao;

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:projectTest/expectedCreateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "projects"
    )
    public void testCreate() throws Exception {
        Category category = categoryDao.getById(1);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Project project = new Project.Builder()
                .setName("New Name")
                .setName("New Name")
                .setShortDescr("New Short Description")
                .setCurrentMoney(1000)
                .setNeedMoney(16000)
                .setAddingDate(format.parse("2014-12-10 00:00:00"))
                .setEndingDate(format.parse("2015-12-06 14:23:18"))
                .setCategory(category)
//                .setFullDescription(fullDescr)
                .setSuccessfullyFinished(false)
                .build();
        projectDao.create(project);
    }
    @Test
    public void testGetById() throws Exception {
        Project project = projectDao.getById(1);
        Assert.assertNotNull(project);
        Assert.assertEquals("name 1", project.getName());
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:projectTest/expectedUpdateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "projects"
    )
    public void testUpdate() throws Exception {
        Project project = projectDao.getById(4);
        project.setName("Updated Name");
        projectDao.update(project);
    }

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:projectTest/expectedDeleteData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "projects"
    )
    public void testDelete() throws Exception {
        Project project = projectDao.getById(4);
        projectDao.delete(project);
    }

    @Test
    public void testGetProjectsOfCategory() throws Exception {
        Category category = categoryDao.getById(1);
        List<Project> projectsOfCategory = projectDao.getProjectsOfCategory(category);
        Assert.assertTrue(projectsOfCategory.size() == 2);
    }
}
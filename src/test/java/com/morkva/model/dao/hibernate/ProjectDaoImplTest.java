package com.morkva.model.dao.hibernate;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.morkva.entities.Category;
import com.morkva.entities.FullDescription;
import com.morkva.entities.Project;
import com.morkva.entities.User;
import com.morkva.model.dao.CategoryDao;
import com.morkva.model.dao.FullDescriptionDao;
import com.morkva.model.dao.ProjectDao;
import com.morkva.model.dao.UserDao;
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
    private ProjectDao projectDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private FullDescriptionDao fullDescriptionDao;

    @Autowired
    private UserDao userDao;

    @Test
    @Rollback(false)
    @ExpectedDatabase(
            value = "classpath:projectTest/expectedCreateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "projects"
    )
    public void testCreate() throws Exception {
        Category category = categoryDao.getById(1);
        FullDescription fullDescription = fullDescriptionDao.getById(7);
        User user = userDao.getById(3);

//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //TODO test date

        Project project = new Project.Builder()
                .setName("New Name")
                .setShortDescr("New Short Description")
                .setCurrentMoney(5000)
                .setNeedMoney(45500)
                .setCategory(category)
                .setUser(user)
                .setFullDescription(fullDescription)
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
        Project project = projectDao.getById(5);
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
        Project project = projectDao.getById(7);
        projectDao.delete(project);
    }

    @Test
    public void testGetProjectsOfCategory() throws Exception {
        Category category = categoryDao.getById(2);
        List<Project> projectsOfCategory = projectDao.getProjectsOfCategory(category);
        Assert.assertEquals(3, projectsOfCategory.size());
    }

    @Test
    public void testGetProjectsOfUser() throws Exception {
        User user = userDao.getById(1);
        List<Project> projectList = projectDao.getProjectsOfUser(user);
        Assert.assertEquals(3, projectList.size());
    }

    @Test
    public void testGetAllFinishedProjects() throws Exception {
        List<Project> projectList = projectDao.getAllFinishedProjects();
        Assert.assertEquals(5,projectList.size());
    }

    @Test
    public void testGetFinishedProjectsOfCategory() throws Exception {
        Category category = categoryDao.getById(1);
        List<Project> projectList = projectDao.getFinishedProjectsOf(category);
        Assert.assertEquals(3, projectList.size());
    }

    @Test
    public void testGetFinishedProjectsOfUser() throws Exception {
        User user = userDao.getById(1);
        List<Project> projectList = projectDao.getFinishedProjectsOf(user);
        Assert.assertEquals(3, projectList.size());
    }

    @Test
    public void testGetNotFinishedProjectsOfCategory() throws Exception {
        Category category = categoryDao.getById(1);
        List<Project> projectList = projectDao.getNotFinishedProjectsOf(category);
        Assert.assertEquals(1, projectList.size());
    }

    @Test
    public void testGetNotFinishedProjectsOfUser() throws Exception {
        User user = userDao.getById(3);
        List<Project> projectList = projectDao.getNotFinishedProjectsOf(user);
        Assert.assertEquals(1, projectList.size());
    }
}
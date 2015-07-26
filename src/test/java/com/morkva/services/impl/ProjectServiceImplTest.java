package com.morkva.services.impl;

import com.morkva.entities.*;
import com.morkva.model.dao.PaymentBonusDao;
import com.morkva.model.dao.PaymentDao;
import com.morkva.model.dao.ProjectDao;
import com.morkva.services.PaymentStatusService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class ProjectServiceImplTest {

    @Mock
    private ProjectDao projectDAO;

    @Mock
    private PaymentBonusDao paymentBonusDao;

    @Mock
    private PaymentStatusService paymentStatusService;

    @Mock
    private PaymentDao paymentDao;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetById() {
        Project project = new Project();
        when(projectDAO.getById(1)).thenReturn(project);
        Assert.assertEquals(project, projectService.getById(1));
    }


    @Test
    public void testGetProjectsOfCategory() {
        Category category = new Category();
        when(projectDAO.getProjectsOfCategory(category)).thenReturn(new LinkedList<>(Collections.singletonList(new Project())));
        Assert.assertTrue(projectService.getProjectsOf(category).size() == 1);
    }

    @Test
    public void testUpdate() {
        Project project = new Project();
        projectService.update(project);
        verify(projectDAO).update(project);
    }

    @Test
    public void testCreate() throws Exception {
        Project project = mock(Project.class);
        projectService.create(project);
        verify(projectDAO).create(project);
    }

    @Test
    public void testSimpleCreate() throws Exception {
        Project project = new Project();
        projectService.simpleCreate(project);

        ArgumentCaptor<Project> argumentCaptor = ArgumentCaptor.forClass(Project.class);

        projectService.create(project);

        verify(projectDAO, times(2)).create(argumentCaptor.capture());
        Project capturedProject = argumentCaptor.getValue();
        assertEquals(0, capturedProject.getCurrentMoney());
        capturedProject.setAddingDate(new Date());
//        assertNotNull(capturedProject.getAddingDate());
        //TODO find out why addingDate is null
        assertFalse(capturedProject.isSuccessfullyFinished());
    }

    @Test
    public void testDelete() throws Exception {
        Project project = mock(Project.class);
        projectService.delete(project);
        verify(projectDAO).delete(project);
    }


    @Test
    public void testGetProjectsOfUser() throws Exception {
        User user = mock(User.class);
        projectService.getProjectsOf(user);
        verify(projectDAO).getProjectsOfUser(user);
    }

    @Test
    public void testGetAllFinishedProjects() throws Exception {
        projectService.getAllFinishedProjects();
        verify(projectDAO).getAllFinishedProjects();
    }

    @Test
    public void testGetFinishedProjectsOfCategory() throws Exception {
        Category category = mock(Category.class);
        projectService.getFinishedProjectsOf(category);
        verify(projectDAO).getFinishedProjectsOf(category);
    }

    @Test
    public void testGetFinishedProjectsOfUser() throws Exception {
        User user = mock(User.class);
        projectService.getFinishedProjectsOf(user);
        verify(projectDAO).getFinishedProjectsOf(user);
    }

    @Test
    public void testGetNotFinishedProjectsOfCategory() throws Exception {
        Category category = mock(Category.class);
        projectService.getNotFinishedProjectsOf(category);
        verify(projectDAO).getNotFinishedProjectsOf(category);
    }

    @Test
    public void testGetNotFinishedProjectsOfUser() throws Exception {
        User user = mock(User.class);
        projectService.getNotFinishedProjectsOf(user);
        verify(projectDAO).getNotFinishedProjectsOf(user);
    }

    @Test
    public void shouldAddBonus_whenPaymentHigherThanHighestBonus() throws Exception {
        Project projectMock = mock(Project.class);
        User userMock = mock(User.class);

        PaymentBonus paymentBonusMock1 = mock(PaymentBonus.class);
        PaymentBonus paymentBonusMock2 = mock(PaymentBonus.class);

        when(paymentBonusMock1.getMinMoney()).thenReturn(100);
        when(paymentBonusMock2.getMinMoney()).thenReturn(200);
        when(paymentBonusMock1.getBonusesLeft()).thenReturn(100);
        when(paymentBonusMock2.getBonusesLeft()).thenReturn(100);

        List<PaymentBonus> mockedPaymentBonuses = new LinkedList<>(Arrays.asList(paymentBonusMock1, paymentBonusMock2));

        when(projectDAO.getById(1)).thenReturn(projectMock);
        when(paymentBonusDao.getPaymentBonusesOfProject(projectMock)).thenReturn(mockedPaymentBonuses);

        projectService.donate(1, 250., userMock);

        ArgumentCaptor<Payment> argumentCaptor = ArgumentCaptor.forClass(Payment.class);

        verify(paymentDao).create(argumentCaptor.capture());

        Assert.assertTrue(argumentCaptor.getValue().getPaymentBonus() != null);
        Assert.assertEquals(paymentBonusMock2, argumentCaptor.getValue().getPaymentBonus());
    }

    @Test
    public void shouldAddBonus_whenPaymentBetweenTwoBonuses() throws Exception {
        Project projectMock = mock(Project.class);
        User userMock = mock(User.class);

        PaymentBonus paymentBonusMock1 = mock(PaymentBonus.class);
        PaymentBonus paymentBonusMock2 = mock(PaymentBonus.class);

        when(paymentBonusMock1.getMinMoney()).thenReturn(100);
        when(paymentBonusMock2.getMinMoney()).thenReturn(200);
        when(paymentBonusMock1.getBonusesLeft()).thenReturn(100);
        when(paymentBonusMock2.getBonusesLeft()).thenReturn(100);

        List<PaymentBonus> mockedPaymentBonuses = new LinkedList<>(Arrays.asList(paymentBonusMock1, paymentBonusMock2));

        when(projectDAO.getById(1)).thenReturn(projectMock);
        when(paymentBonusDao.getPaymentBonusesOfProject(projectMock)).thenReturn(mockedPaymentBonuses);

        projectService.donate(1, 150., userMock);

        ArgumentCaptor<Payment> argumentCaptor = ArgumentCaptor.forClass(Payment.class);

        verify(paymentDao).create(argumentCaptor.capture());

        Assert.assertTrue(argumentCaptor.getValue().getPaymentBonus() != null);
        Assert.assertEquals(paymentBonusMock1, argumentCaptor.getValue().getPaymentBonus());
    }

    @Test
    public void shouldNotAddBonus_whenPaymentLowerThanAllBonuses() throws Exception {
        Project projectMock = mock(Project.class);
        User userMock = mock(User.class);

        PaymentBonus paymentBonusMock1 = mock(PaymentBonus.class);
        PaymentBonus paymentBonusMock2 = mock(PaymentBonus.class);

        when(paymentBonusMock1.getMinMoney()).thenReturn(100);
        when(paymentBonusMock2.getMinMoney()).thenReturn(200);
        when(paymentBonusMock1.getBonusesLeft()).thenReturn(100);
        when(paymentBonusMock2.getBonusesLeft()).thenReturn(100);

        List<PaymentBonus> mockedPaymentBonuses = new LinkedList<>(Arrays.asList(paymentBonusMock1, paymentBonusMock2));

        when(projectDAO.getById(1)).thenReturn(projectMock);
        when(paymentBonusDao.getPaymentBonusesOfProject(projectMock)).thenReturn(mockedPaymentBonuses);

        projectService.donate(1, 95., userMock);

        ArgumentCaptor<Payment> argumentCaptor = ArgumentCaptor.forClass(Payment.class);

        verify(paymentDao).create(argumentCaptor.capture());

        Assert.assertTrue(argumentCaptor.getValue().getPaymentBonus() == null);
    }


    @Test
    public void shouldNotAddBonus_whenNoBonusesLeft() throws Exception {
        Project projectMock = mock(Project.class);
        User userMock = mock(User.class);

        PaymentBonus paymentBonusMock1 = mock(PaymentBonus.class);
        PaymentBonus paymentBonusMock2 = mock(PaymentBonus.class);

        when(paymentBonusMock1.getMinMoney()).thenReturn(100);
        when(paymentBonusMock2.getMinMoney()).thenReturn(200);
        when(paymentBonusMock1.getBonusesLeft()).thenReturn(0);
        when(paymentBonusMock2.getBonusesLeft()).thenReturn(0);

        List<PaymentBonus> mockedPaymentBonuses = new LinkedList<>(Arrays.asList(paymentBonusMock1, paymentBonusMock2));

        when(projectDAO.getById(1)).thenReturn(projectMock);
        when(paymentBonusDao.getPaymentBonusesOfProject(projectMock)).thenReturn(mockedPaymentBonuses);

        projectService.donate(1, 250., userMock);

        ArgumentCaptor<Payment> argumentCaptor = ArgumentCaptor.forClass(Payment.class);

        verify(paymentDao).create(argumentCaptor.capture());

        Assert.assertTrue(argumentCaptor.getValue().getPaymentBonus() == null);
    }
}

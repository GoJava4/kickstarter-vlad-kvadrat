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

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
        Assert.assertTrue(projectService.getProjectsOfCategory(category).size() == 1);
    }

    @Test
    public void testUpdate() {
        Project project = new Project();
        projectService.update(project);
        verify(projectDAO).update(project);
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

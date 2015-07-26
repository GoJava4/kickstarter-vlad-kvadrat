package com.morkva.services.impl;

import com.morkva.entities.UserRole;
import com.morkva.model.dao.UserRoleDao;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserRoleServiceImplTest {

    @Mock
    UserRoleDao userRoleDao;

    @InjectMocks
    UserRoleServiceImpl userRoleService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreate() throws Exception {
        UserRole userRole = mock(UserRole.class);
        userRoleService.create(userRole);
        userRoleDao.create(userRole);
    }

    @Test
    public void testGetById() throws Exception {
        UserRole userRole = mock(UserRole.class);
        int id = 1;
        when(userRoleDao.getById(id)).thenReturn(userRole);
        Assert.assertEquals(userRole, userRoleService.getById(id));
        verify(userRoleDao).getById(id); //not necessary
    }

    @Test
    public void testDelete() throws Exception {
        UserRole userRole = mock(UserRole.class);
        userRoleService.delete(userRole);
        userRoleDao.delete(userRole);
    }
}
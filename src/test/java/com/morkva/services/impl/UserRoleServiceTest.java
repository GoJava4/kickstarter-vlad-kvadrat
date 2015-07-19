package com.morkva.services.impl;

import com.morkva.entities.UserRole;
import com.morkva.model.dao.UserRoleDao;
import com.morkva.services.impl.UserRoleServiceImpl;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class UserRoleServiceTest {

    @Mock
    private UserRoleDao userRoleDao;

    @InjectMocks
    private UserRoleServiceImpl userRoleService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreate() throws Exception {
        UserRole userRole = new UserRole();
        userRoleService.create(userRole);
        Mockito.verify(userRoleDao).create(userRole);
    }

    @Test
    public void testGetById() throws Exception {
        Integer id = 1;
        UserRole userRole = new UserRole();

        Mockito.when(userRoleDao.getById(id)).thenReturn(userRole);
        Assert.assertEquals(userRole, userRoleService.getById(id));
    }

    @Test
    public void testUpdate() throws Exception {
        UserRole userRole = new UserRole();
        userRoleService.update(userRole);
        Mockito.verify(userRoleDao).update(userRole);
    }

    @Test
    public void testDelete() throws Exception {
        UserRole userRole = new UserRole();
        userRoleService.delete(userRole);
        Mockito.verify(userRoleDao).delete(userRole);
    }
}
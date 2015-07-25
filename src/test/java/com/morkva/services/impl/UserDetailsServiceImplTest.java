package com.morkva.services.impl;

import com.morkva.entities.Comment;
import com.morkva.entities.User;
import com.morkva.entities.UserRole;
import com.morkva.model.dao.UserDao;
import com.morkva.model.dao.UserRoleDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserDetailsServiceImplTest {

    @Mock
    private UserDao userDao;

    @Mock
    private UserRoleDao userRoleDao;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserByLogin() throws Exception {
        User user = mock(User.class);
        when(userDao.getByLogin(anyString())).thenReturn(user);
        Assert.assertEquals(user, userDetailsService.getUserByLogin(anyString()));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void shouldException_whenUserNotFound() throws Exception {
        when(userDao.getByLogin(anyString())).thenReturn(null);
        userDetailsService.loadUserByUsername(anyString());
    }

    @Test
    public void shouldReturnUserDetails_whenUserExists() {
        UserRole userRole = mock(UserRole.class);
        User user = new User();
        user.setActive(true);
        user.setRole(userRole);
        user.setLogin("username");
        user.setPassword("password");

        when(userRole.getName()).thenReturn("ADMIN");
        when(userDao.getByLogin(anyString())).thenReturn(user);
        UserDetails userDetails = userDetailsService.loadUserByUsername(anyString());
        Assert.assertNotNull(userDetails);
    }

    @Test
    public void testGetById() throws Exception {
        User user = new User();
        when(userDao.getById(1)).thenReturn(user);
        Assert.assertEquals(user, userDetailsService.getById(1));
    }

    @Test
    public void testCreate() throws Exception {
        User user = mock(User.class);
        when(userRoleDao.getById(2)).thenReturn(mock(UserRole.class));
        userDetailsService.create(user);
        verify(userDao).create(user);
    }

    @Test
    public void testUpdate() throws Exception {
        User user = mock(User.class);
        userDetailsService.update(user);
        verify(userDao).update(user);
    }

    @Test
    public void testDelete() throws Exception {
        User user = mock(User.class);
        userDetailsService.delete(user);
        verify(userDao).delete(user);
    }

    //tests spring overrided method
    //just in general
    @Test
    @Ignore
    public void testLoadUserByUsername() throws Exception {
        User user = mock(User.class);
        String userName = anyString();
        String roleName = "ROLE_USER";
        when(userDao.getByLogin(userName)).thenReturn(user);
        when(user.getRole()).thenReturn(mock(UserRole.class));
        when(user.getRole().getName()).thenReturn(roleName);
        userDetailsService.loadUserByUsername(userName);
        verify(userDao).getByLogin(userName);
    }

    @Test (expected = UsernameNotFoundException.class)
    public void shouldThrowUsernameNotFoundException_whenNoUserWithSuchLogin() throws Exception {
        String userName = anyString();
        when(userDao.getByLogin(userName)).thenReturn(null);
        userDetailsService.loadUserByUsername(userName);
    }
}
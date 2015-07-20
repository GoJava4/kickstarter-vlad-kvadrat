package com.morkva.services.impl;

import com.morkva.entities.User;
import com.morkva.entities.UserRole;
import com.morkva.model.dao.UserDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.mockito.Mockito.*;

public class UserDetailsServiceImplTest {

    @Mock
    private UserDao userDao;

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
}
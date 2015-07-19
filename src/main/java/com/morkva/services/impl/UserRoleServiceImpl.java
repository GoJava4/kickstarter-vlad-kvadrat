package com.morkva.services.impl;

import com.morkva.entities.UserRole;
import com.morkva.model.dao.UserRoleDao;
import com.morkva.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public void create(UserRole userRole) {
        userRoleDao.create(userRole);
    }

    @Override
    public UserRole getById(Integer id) {
        return userRoleDao.getById(id);
    }

    @Override
    public void update(UserRole userRole) {
        userRoleDao.update(userRole);
    }

    @Override
    public void delete(UserRole userRole) {
        userRoleDao.delete(userRole);
    }
}

package com.morkva.services;

import com.morkva.entities.UserRole;
import com.morkva.model.dao.UserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by koros on 11.07.2015.
 */
@Service("userRoleService")
public class UserRoleService {

    @Autowired
    UserRoleDao userRoleDao;

    public void create(UserRole userRole) {
        userRoleDao.create(userRole);
    }

    public UserRole getById(Integer id) {
        return userRoleDao.getById(id);
    }

    public void update(UserRole userRole) {
        userRoleDao.update(userRole);
    }

    public void delete(UserRole userRole) {
        userRoleDao.delete(userRole);
    }
}

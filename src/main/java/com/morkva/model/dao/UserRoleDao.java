package com.morkva.model.dao;

import com.morkva.entities.UserRole;

public interface UserRoleDao extends Dao<UserRole> {
    UserRole getByName(String role_name);
}

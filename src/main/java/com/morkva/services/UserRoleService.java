package com.morkva.services;

import com.morkva.entities.UserRole;

public interface UserRoleService {
    void create(UserRole userRole);

    UserRole getById(Integer id);

    void delete(UserRole userRole);
}

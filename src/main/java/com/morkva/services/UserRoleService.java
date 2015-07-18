package com.morkva.services;

import com.morkva.entities.UserRole;

public interface UserRoleService {
    void create(UserRole userRole);

    UserRole getById(Integer id);

    void update(UserRole userRole);

    void delete(UserRole userRole);
}

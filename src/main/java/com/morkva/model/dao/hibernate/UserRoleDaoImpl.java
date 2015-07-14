package com.morkva.model.dao.hibernate;

import com.morkva.entities.UserRole;
import com.morkva.model.dao.UserRoleDao;
import org.springframework.stereotype.Repository;

@Repository("userRoleDao")
public class UserRoleDaoImpl extends AbstractDao<UserRole> implements UserRoleDao {

    public UserRoleDaoImpl() {
        super(UserRole.class);
    }
}

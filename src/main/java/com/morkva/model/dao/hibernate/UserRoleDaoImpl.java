package com.morkva.model.dao.hibernate;

import com.morkva.entities.UserRole;
import com.morkva.model.dao.UserRoleDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("userRoleDao")
public class UserRoleDaoImpl extends AbstractDao<UserRole> implements UserRoleDao {

    public UserRoleDaoImpl() {
        super(UserRole.class);
    }

    @Override
    public UserRole getByName(String role_name) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("from UserRole where name = :name");
        query.setParameter("name", role_name);
        return (UserRole) query.uniqueResult();
    }
}

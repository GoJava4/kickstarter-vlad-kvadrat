package com.morkva.model.dao.hibernate;

import com.morkva.entities.User;
import com.morkva.model.dao.UserDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * Created by koros on 11.07.2015.
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User getByLogin(String login) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("from User where login = :login");
        query.setParameter("login", login);
        User user = (User) query.uniqueResult();
        return user;
    }

    @Override
    public void activateUser(Integer id) {
        changeUserStatus(id, true);
    }

    @Override
    public void deactivateUser(Integer id) {
        changeUserStatus(id, false);
    }

    private void changeUserStatus(Integer id, boolean status) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("update User set active = :active where id = :id");
        query.setBoolean("active", status);
        query.setInteger("id", id);
        query.executeUpdate();
    }
}

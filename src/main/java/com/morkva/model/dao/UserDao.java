package com.morkva.model.dao;

import com.morkva.entities.User;

/**
 * Created by koros on 11.07.2015.
 */
public interface UserDao extends Dao<User> {
    User getByLogin(String login);
    void activateUser(Integer id);
    void deactivateUser(Integer id);
}

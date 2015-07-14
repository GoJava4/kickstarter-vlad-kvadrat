package com.morkva.model.dao;

import com.morkva.entities.User;

public interface UserDao extends Dao<User> {
    User getByLogin(String login);
    void activateUser(Integer id);
    void deactivateUser(Integer id);
}

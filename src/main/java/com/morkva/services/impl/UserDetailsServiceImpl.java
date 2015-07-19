package com.morkva.services.impl;

import com.morkva.entities.User;
import com.morkva.model.dao.UserDao;
import com.morkva.services.UserDetailsServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by koros on 11.07.2015.
 */
@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsServiceExtended {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByLogin(String login) {
        return userDao.getByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Assembler assembler = new Assembler();
        User user = userDao.getByLogin(s);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return assembler.buildUserFromUserEntity(user);
    }

    private class Assembler {
        org.springframework.security.core.userdetails.User buildUserFromUserEntity(com.morkva.entities.User userEntity) {
            String username = userEntity.getLogin();
            String password = userEntity.getPassword();
            boolean enabled = userEntity.isActive();
            boolean accountNonExpired = userEntity.isActive();
            boolean credentialsNonExpired = userEntity.isActive();
            boolean accountNonLocked = userEntity.isActive();
            Collection<SimpleGrantedAuthority> authorities = new LinkedList<>();
            authorities.add(new SimpleGrantedAuthority(userEntity.getRole().getName()));
            org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
            return user;
        }
    }
}

package com.morkva.entities;

import javax.persistence.*;

/**
 * Created by koros on 11.07.2015.
 */
@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue
    private Integer id;

    private String login;
    private String password;

    @ManyToOne @JoinColumn(name = "role_id")
    private UserRole role;

    private boolean active;

    private String username;

    private String email;

    @Column(name = "personal_info")
    private String personalInfo;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(String personalInfo) {
        this.personalInfo = personalInfo;
    }
}

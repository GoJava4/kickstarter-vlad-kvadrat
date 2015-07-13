package com.morkva.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    public UserRole() {
    }

    public String getName() {
        return name;
    }
}

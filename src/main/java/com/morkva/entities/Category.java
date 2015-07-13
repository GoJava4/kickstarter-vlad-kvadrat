package com.morkva.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by vladyslav on 02.05.15.
 */
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

}

package com.morkva.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by vladyslav on 30.04.15.
 */
@Entity
@Table(name = "quotes")
public class Quote {

    @Id
    @GeneratedValue
    private Integer id;

    private String value;
    private String author;

    public Quote(String value, String author) {
        this.value = value;
        this.author = author;
    }

    public Quote() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

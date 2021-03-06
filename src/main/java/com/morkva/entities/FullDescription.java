package com.morkva.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "full_descriptions")
public class FullDescription {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "full_description")
    @NotNull
    private String value;

    public FullDescription() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

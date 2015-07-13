package com.morkva.entities;

import javax.persistence.*;

@Entity
@Table(name = "pictures")
public class Picture {

    @Id @GeneratedValue
    private Integer id;

//    @Lob
    @Column(nullable = false)
    private byte[] image;

    public Picture() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

package com.morkva.model.dao.hibernate;

import com.morkva.entities.Picture;
import com.morkva.model.dao.PictureDao;
import org.springframework.stereotype.Repository;

@Repository("pictureDao")
public class PictureDaoImpl extends AbstractDao<Picture> implements PictureDao {
    public PictureDaoImpl() {
        super(Picture.class);
    }
}

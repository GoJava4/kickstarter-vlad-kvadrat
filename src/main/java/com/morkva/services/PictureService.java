package com.morkva.services;

import com.morkva.entities.Picture;
import com.morkva.model.dao.PictureDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("pictureService")
@Transactional
public class PictureService {

    @Autowired
    private PictureDao pictureDao;

    public Picture getById(Integer id) {
        return pictureDao.getById(id);
    }
}

package com.morkva.services.impl;

import com.morkva.entities.Picture;
import com.morkva.model.dao.PictureDao;
import com.morkva.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("pictureService")
@Transactional
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureDao pictureDao;

    @Override
    public Picture getById(Integer id) {
        return pictureDao.getById(id);
    }
}

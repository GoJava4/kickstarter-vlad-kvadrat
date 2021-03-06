package com.morkva.services.impl;

import com.morkva.entities.FullDescription;
import com.morkva.model.dao.FullDescriptionDao;
import com.morkva.services.FullDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("fullDescriptionService")
public class FullDescriptionServiceImpl implements FullDescriptionService {

    @Autowired
    private FullDescriptionDao fullDescriptionDao;

    @Override
    public void create(FullDescription fullDescription) {
        fullDescriptionDao.create(fullDescription);
    }
}

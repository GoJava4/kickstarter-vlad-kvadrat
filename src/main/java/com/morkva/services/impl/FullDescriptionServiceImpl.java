package com.morkva.services.impl;

import com.morkva.model.dao.FullDescriptionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("fullDescriptionService")
public class FullDescriptionServiceImpl {

    @Autowired
    private FullDescriptionDao fullDescriptionDao;
}

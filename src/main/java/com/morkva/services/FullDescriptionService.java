package com.morkva.services;

import com.morkva.model.dao.FullDescriptionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("fullDescriptionService")
public class FullDescriptionService {


    @Qualifier("fullDescriptionDao")
    @Autowired
    private FullDescriptionDao fullDescriptionDao;
}

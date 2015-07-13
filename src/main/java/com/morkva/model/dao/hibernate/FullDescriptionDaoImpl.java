package com.morkva.model.dao.hibernate;

import com.morkva.entities.FullDescription;
import com.morkva.model.dao.FullDescriptionDao;
import org.springframework.stereotype.Repository;

@Repository("fullDescriptionDao")
public class FullDescriptionDaoImpl extends AbstractDao<FullDescription> implements FullDescriptionDao {

    public FullDescriptionDaoImpl() {
        super(FullDescription.class);
    }
}

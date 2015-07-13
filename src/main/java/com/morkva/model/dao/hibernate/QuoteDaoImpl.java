package com.morkva.model.dao.hibernate;

import com.morkva.entities.Quote;
import com.morkva.model.dao.QuoteDao;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * Created by koros on 05.07.2015.
 */
@Repository("quoteDao")
public class QuoteDaoImpl extends AbstractDao<Quote> implements QuoteDao {


    public QuoteDaoImpl() {
        super(Quote.class);
    }

    @Override
    public Quote getRandom() {
        Session currentSession = sessionFactory.getCurrentSession();
        return (Quote) currentSession.createSQLQuery("SELECT * FROM quotes ORDER BY RAND() LIMIT 1").addEntity(Quote.class).uniqueResult();
    }
}

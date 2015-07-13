package com.morkva.services;

import com.morkva.entities.Quote;
import com.morkva.model.dao.QuoteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by koros on 30.06.2015.
 */
@Service("quoteService")
@Transactional
public class QuoteService {

    @Autowired
    QuoteDao quoteDAO;

    public Quote getRandom() {
        return quoteDAO.getRandom();
    }
}

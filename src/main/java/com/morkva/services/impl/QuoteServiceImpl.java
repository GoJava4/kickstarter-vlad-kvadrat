package com.morkva.services.impl;

import com.morkva.entities.Quote;
import com.morkva.model.dao.QuoteDao;
import com.morkva.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("quoteService")
@Transactional
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteDao quoteDAO;

    @Override
    public Quote getRandom() {
        return quoteDAO.getRandom();
    }
}

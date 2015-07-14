package com.morkva.model.dao;


import com.morkva.entities.Quote;

public interface QuoteDao extends Dao<Quote> {

    Quote getRandom();
}

package com.morkva.model.dao;


import com.morkva.entities.Quote;

/**
 * Created by koros on 30.06.2015.
 */
public interface QuoteDao extends Dao<Quote> {

    Quote getRandom();
}

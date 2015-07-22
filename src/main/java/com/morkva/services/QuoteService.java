package com.morkva.services;

import com.morkva.entities.Quote;

public interface QuoteService {
    Quote getRandom();

    void create(Quote quote);

    Quote getById(int id);

    void update(Quote quote);
}

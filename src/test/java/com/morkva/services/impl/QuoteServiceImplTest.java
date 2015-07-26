package com.morkva.services.impl;

import com.morkva.entities.Quote;
import com.morkva.model.dao.QuoteDao;
import com.morkva.services.impl.QuoteServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class QuoteServiceImplTest {

    @Mock
    private QuoteDao quoteDAO;

    @InjectMocks
    private QuoteServiceImpl quoteService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetRandom() throws Exception {
        Quote quote = mock(Quote.class);
        when(quoteDAO.getRandom()).thenReturn(quote);
        assertEquals(quote, quoteService.getRandom());
    }

    @Test
    public void testCreate() throws Exception {
        Quote quote = mock(Quote.class);
        quoteService.create(quote);
        verify(quoteDAO).create(quote);
    }

    @Test
    public void testGetById() throws Exception {
        Quote quote = mock(Quote.class);
        when(quoteDAO.getById(anyInt())).thenReturn(quote);
        assertEquals(quote, quoteService.getById(anyInt()));
    }

    @Test
    public void testUpdate() throws Exception {
        Quote quote = mock(Quote.class);
        quoteService.update(quote);
        verify(quoteDAO).update(quote);
    }
}
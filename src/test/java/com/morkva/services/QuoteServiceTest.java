package com.morkva.services;

import com.morkva.model.dao.QuoteDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class QuoteServiceTest {

    @Mock
    QuoteDao quoteDAO;

    @InjectMocks
    QuoteService quoteService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetRandom() throws Exception {
        Mockito.when(quoteDAO.getRandom()).thenReturn(null);

        Assert.assertEquals(null, quoteService.getRandom());
    }
}
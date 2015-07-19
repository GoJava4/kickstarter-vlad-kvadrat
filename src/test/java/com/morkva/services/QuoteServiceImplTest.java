package com.morkva.services;

import com.morkva.model.dao.QuoteDao;
import com.morkva.services.impl.QuoteServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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
        Mockito.when(quoteDAO.getRandom()).thenReturn(null);

        Assert.assertEquals(null, quoteService.getRandom());
    }
}
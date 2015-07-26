package com.morkva.services.impl;

import com.morkva.entities.FullDescription;
import com.morkva.model.dao.FullDescriptionDao;
import com.morkva.services.FullDescriptionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FullDescriptionServiceImplTest {

    @Mock
    FullDescriptionDao fullDescrDao;

    @InjectMocks
    FullDescriptionServiceImpl fullDescrService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreate() throws Exception {
        FullDescription fullDescription = mock(FullDescription.class);
        fullDescrService.create(fullDescription);
        verify(fullDescrDao).create(fullDescription);
    }
}
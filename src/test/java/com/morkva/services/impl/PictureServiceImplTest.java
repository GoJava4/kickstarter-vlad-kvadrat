package com.morkva.services.impl;

import com.morkva.entities.Picture;
import com.morkva.model.dao.PictureDao;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PictureServiceImplTest {

    @Mock
    PictureDao pictureDao;

    @InjectMocks
    PictureServiceImpl pictureService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetById() throws Exception {
        Picture picture = mock(Picture.class);
        int id = 1;
        when(pictureDao.getById(id)).thenReturn(picture);
        Assert.assertEquals(picture, pictureService.getById(id));
        verify(pictureDao).getById(id);
    }
}
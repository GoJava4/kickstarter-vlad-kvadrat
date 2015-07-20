package com.morkva.services.impl;

import com.morkva.entities.Picture;
import com.morkva.model.dao.PictureDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class PictureServiceTest {

    @Mock
    private PictureDao pictureDao;

    @InjectMocks
    private PictureServiceImpl pictureService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetById() throws Exception {
        Integer id = 1;
        Picture picture = new Picture();
        Mockito.when(pictureDao.getById(id)).thenReturn(picture);
        Assert.assertEquals(picture, pictureService.getById(id));
    }
}
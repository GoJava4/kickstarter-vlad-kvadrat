package com.morkva.services;

import com.morkva.entities.Picture;
import com.morkva.model.dao.PictureDao;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class PictureServiceTest {

    @Mock
    PictureDao pictureDao;

    @InjectMocks
    PictureService pictureService;

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
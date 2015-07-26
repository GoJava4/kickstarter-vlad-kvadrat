package com.morkva.services.impl;

import com.morkva.entities.PaymentStatus;
import com.morkva.model.dao.PaymentStatusDao;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PaymentStatusServiceImplTest {

    @Mock
    PaymentStatusDao paymentStatusDao;

    @InjectMocks
    PaymentStatusServiceImpl paymentStatusService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetById() throws Exception {
        PaymentStatus paymentStatus = mock(PaymentStatus.class);
        int id = 1;
        when(paymentStatusDao.getById(id)).thenReturn(paymentStatus);
        Assert.assertEquals(paymentStatus, paymentStatusService.getById(id));
        verify(paymentStatusDao).getById(id);
    }
}
package com.morkva.services;

import com.morkva.entities.PaymentStatus;
import com.morkva.model.dao.PaymentStatusDao;
import com.morkva.services.impl.PaymentStatusServiceImpl;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class PaymentStatusServiceTest {

    @Mock
    private PaymentStatusDao paymentStatusDao;

    @InjectMocks
    private PaymentStatusServiceImpl paymentStatusService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetById() throws Exception {
        Integer id = 1;
        PaymentStatus mock = new PaymentStatus();
        Mockito.when(paymentStatusDao.getById(id)).thenReturn(mock);
        Assert.assertEquals(mock, paymentStatusService.getById(id));
    }
}
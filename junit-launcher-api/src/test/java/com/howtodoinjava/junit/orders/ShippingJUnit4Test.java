package com.howtodoinjava.junit.orders;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShippingJUnit4Test {

    private final OrderValidator validator = new OrderValidator();

    @Test
    public void smallOrderPaysShipping() {
        assertEquals(499, validator.shippingCost(1999));
    }

    @Test
    public void largeOrderShipsFree() {
        assertEquals(0, validator.shippingCost(7500));
    }
}

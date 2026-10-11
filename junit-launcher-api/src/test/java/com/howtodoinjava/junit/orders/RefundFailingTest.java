package com.howtodoinjava.junit.orders;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Fails on purpose to show how failures are reported. Excluded from mvn test in pom.xml.
class RefundFailingTest {

    @Test
    void refundKeepsShippingCost() {
        assertEquals(499, new OrderValidator().shippingCost(9900));
    }

    @Test
    void refundOfSmallOrder() {
        assertEquals(499, new OrderValidator().shippingCost(1000));
    }
}

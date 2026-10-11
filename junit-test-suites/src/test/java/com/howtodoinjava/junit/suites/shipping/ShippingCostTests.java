package com.howtodoinjava.junit.suites.shipping;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShippingCostTests {

    @Test
    void lightParcelCostsLess() {
        assertEquals(new BigDecimal("4.99"), new ShippingCost().forWeight(1.5));
    }
}

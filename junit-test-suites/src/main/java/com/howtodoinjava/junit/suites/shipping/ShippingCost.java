package com.howtodoinjava.junit.suites.shipping;

import java.math.BigDecimal;

public class ShippingCost {

    public BigDecimal forWeight(double kilograms) {
        return kilograms <= 2 ? new BigDecimal("4.99") : new BigDecimal("9.99");
    }

    public String label(String city) {
        return "SHIP TO " + city.toUpperCase();
    }
}

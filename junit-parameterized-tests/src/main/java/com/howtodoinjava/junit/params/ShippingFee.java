package com.howtodoinjava.junit.params;

import java.math.BigDecimal;

public class ShippingFee {

    public BigDecimal forWeight(double kilograms) {
        if (kilograms <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        if (kilograms <= 1) {
            return new BigDecimal("3.99");
        }
        return kilograms <= 5 ? new BigDecimal("7.99") : new BigDecimal("14.99");
    }
}

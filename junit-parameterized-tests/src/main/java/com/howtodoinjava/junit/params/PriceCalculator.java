package com.howtodoinjava.junit.params;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceCalculator {

    public BigDecimal finalPrice(BigDecimal unitPrice, int quantity, MembershipTier tier) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        BigDecimal gross = unitPrice.multiply(BigDecimal.valueOf(quantity));
        BigDecimal factor = BigDecimal.valueOf(100 - tier.discountPercent()).movePointLeft(2);
        return gross.multiply(factor).setScale(2, RoundingMode.HALF_UP);
    }
}

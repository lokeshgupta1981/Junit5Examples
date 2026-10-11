package com.howtodoinjava.junit.params;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.Parameter;
import org.junit.jupiter.params.ParameterizedClass;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ParameterizedClass
@EnumSource(MembershipTier.class)
class TierPricingTest {

    @Parameter
    MembershipTier tier;

    private final PriceCalculator calculator = new PriceCalculator();

    @BeforeEach
    void printName(TestInfo info) {
        System.out.println(tier + " > " + info.getDisplayName());
    }

    @Test
    void neverChargesMoreThanListPrice() {
        BigDecimal price = calculator.finalPrice(new BigDecimal("20.00"), 1, tier);
        assertTrue(price.compareTo(new BigDecimal("20.00")) <= 0);
    }

    @Test
    void keepsTwoDecimalPlaces() {
        assertEquals(2, calculator.finalPrice(new BigDecimal("9.99"), 3, tier).scale());
    }
}

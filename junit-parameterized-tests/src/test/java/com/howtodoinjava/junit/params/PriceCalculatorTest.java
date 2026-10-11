package com.howtodoinjava.junit.params;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceCalculatorTest {

    private final PriceCalculator calculator = new PriceCalculator();

    @BeforeEach
    void printName(TestInfo info) {
        System.out.println(info.getDisplayName());
    }

    @ParameterizedTest(name = "{2}: {1} x {0} = {3}", quoteTextArguments = false)
    @CsvSource({
        "10.00, 2, BASIC,  20.00",
        "10.00, 2, SILVER, 19.00",
        "10.00, 2, GOLD,   18.00"
    })
    void appliesTierDiscount(BigDecimal unitPrice, int quantity, MembershipTier tier, BigDecimal expected) {
        assertEquals(expected, calculator.finalPrice(unitPrice, quantity, tier));
    }
}

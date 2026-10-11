package com.howtodoinjava.junit.params;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ShippingFeeTest {

    private final ShippingFee shippingFee = new ShippingFee();

    @BeforeEach
    void printName(TestInfo info) {
        System.out.println(info.getDisplayName());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.5, 1.0})
    void chargesLowestFeeUpToOneKilogram(double weight) {
        assertEquals(new BigDecimal("3.99"), shippingFee.forWeight(weight));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -1.5})
    void rejectsZeroOrNegativeWeight(double weight) {
        assertThrows(IllegalArgumentException.class, () -> shippingFee.forWeight(weight));
    }

    @ParameterizedTest(name = "{0} kg -> {1}")
    @CsvFileSource(resources = "/shipping-fees.csv", numLinesToSkip = 1)
    void chargesFeeFromTable(double weight, BigDecimal expectedFee) {
        assertEquals(expectedFee, shippingFee.forWeight(weight));
    }
}

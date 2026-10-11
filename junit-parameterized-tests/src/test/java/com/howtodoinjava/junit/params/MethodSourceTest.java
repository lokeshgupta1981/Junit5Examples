package com.howtodoinjava.junit.params;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class MethodSourceTest {

    private final PriceCalculator calculator = new PriceCalculator();

    @BeforeEach
    void printName(TestInfo info) {
        System.out.println(info.getDisplayName());
    }

    static Stream<Arguments> smallOrders() {
        return Stream.of(
                arguments(new BigDecimal("12.50"), 1, MembershipTier.BASIC, new BigDecimal("12.50")),
                arguments(new BigDecimal("12.50"), 3, MembershipTier.SILVER, new BigDecimal("35.63")));
    }

    @ParameterizedTest
    @MethodSource("smallOrders")
    void pricesSmallOrders(BigDecimal unitPrice, int quantity, MembershipTier tier, BigDecimal expected) {
        assertEquals(expected, calculator.finalPrice(unitPrice, quantity, tier));
    }

    @ParameterizedTest
    @MethodSource("com.howtodoinjava.junit.params.OrderData#largeOrders")
    void pricesLargeOrders(BigDecimal unitPrice, int quantity, MembershipTier tier, BigDecimal expected) {
        assertEquals(expected, calculator.finalPrice(unitPrice, quantity, tier));
    }

    static Stream<Integer> rejectsInvalidQuantity() {
        return Stream.of(0, -1);
    }

    @ParameterizedTest
    @MethodSource
    void rejectsInvalidQuantity(int quantity) {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.finalPrice(BigDecimal.TEN, quantity, MembershipTier.BASIC));
    }
}

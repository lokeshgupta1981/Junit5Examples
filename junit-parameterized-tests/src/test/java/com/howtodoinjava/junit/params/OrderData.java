package com.howtodoinjava.junit.params;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import static org.junit.jupiter.params.provider.Arguments.argumentSet;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class OrderData {

    public static Stream<Arguments> largeOrders() {
        return Stream.of(
                arguments(new BigDecimal("4.50"), 100, MembershipTier.GOLD, new BigDecimal("405.00")),
                argumentSet("bulk order of a cheap item", new BigDecimal("0.99"), 1000, MembershipTier.BASIC,
                        new BigDecimal("990.00")));
    }
}

package com.howtodoinjava.junit.suites.orders;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("fast")
class OrderTotalsTest {

    @Test
    void sumsAllLines() {
        List<OrderService.Line> lines = List.of(
                new OrderService.Line("coffee", 2, new BigDecimal("3.50")),
                new OrderService.Line("bagel", 1, new BigDecimal("2.00")));
        assertEquals(new BigDecimal("9.00"), new OrderService().total(lines));
    }
}

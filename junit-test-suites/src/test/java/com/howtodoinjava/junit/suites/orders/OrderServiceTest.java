package com.howtodoinjava.junit.suites.orders;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderServiceTest {

    private final OrderService service = new OrderService();

    @Test
    void placesOrder() {
        List<OrderService.Line> lines = List.of(new OrderService.Line("coffee", 2, new BigDecimal("3.50")));
        assertEquals("PLACED", service.place(lines));
    }

    @Test
    void rejectsEmptyOrder() {
        assertThrows(IllegalArgumentException.class, () -> service.place(List.of()));
    }
}

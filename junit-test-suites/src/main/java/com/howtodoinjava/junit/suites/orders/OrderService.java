package com.howtodoinjava.junit.suites.orders;

import java.math.BigDecimal;
import java.util.List;

public class OrderService {

    public record Line(String item, int quantity, BigDecimal price) {
    }

    public BigDecimal total(List<Line> lines) {
        return lines.stream()
                .map(l -> l.price().multiply(BigDecimal.valueOf(l.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String place(List<Line> lines) {
        if (lines.isEmpty()) {
            throw new IllegalArgumentException("An order needs at least one line");
        }
        return "PLACED";
    }
}

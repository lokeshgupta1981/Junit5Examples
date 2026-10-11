package com.howtodoinjava.junit.cart;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Fails on purpose to show a failure report. Excluded in pom.xml, run with -Dtest=ShoppingCartFailingTest
class ShoppingCartFailingTest {

    @Test
    void totalWithWrongExpectation() {
        ShoppingCart cart = new ShoppingCart();
        cart.add("apple", new BigDecimal("0.50"), 4);

        assertEquals(new BigDecimal("2.50"), cart.total());
    }
}

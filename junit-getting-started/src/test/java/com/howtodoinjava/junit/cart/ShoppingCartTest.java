package com.howtodoinjava.junit.cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShoppingCartTest {

    private ShoppingCart cart;

    @BeforeEach
    void createEmptyCart() {
        cart = new ShoppingCart();
    }

    @Test
    void newCartIsEmpty() {
        assertTrue(cart.isEmpty());
    }

    @Test
    @DisplayName("Total adds price times quantity of every item")
    void totalOfTwoItems() {
        cart.add("apple", new BigDecimal("0.50"), 4);
        cart.add("bread", new BigDecimal("2.25"), 1);

        assertEquals(new BigDecimal("4.25"), cart.total());
    }

    @Test
    void itemCountSumsQuantities() {
        cart.add("apple", new BigDecimal("0.50"), 4);
        cart.add("bread", new BigDecimal("2.25"), 1);

        assertAll(
                () -> assertEquals(5, cart.itemCount()),
                () -> assertEquals(new BigDecimal("4.25"), cart.total()));
    }

    @Test
    void rejectsZeroQuantity() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> cart.add("apple", new BigDecimal("0.50"), 0));

        assertEquals("Quantity must be positive: 0", ex.getMessage());
    }
}

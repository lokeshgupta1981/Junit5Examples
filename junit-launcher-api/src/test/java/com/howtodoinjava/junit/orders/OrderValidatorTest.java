package com.howtodoinjava.junit.orders;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderValidatorTest {

    private final OrderValidator validator = new OrderValidator();

    @Test
    @Tag("checkout")
    void acceptsQuantityWithinStock() {
        assertTrue(validator.isValid(2, 5));
    }

    @Test
    @Tag("checkout")
    void rejectsQuantityAboveStock() {
        assertFalse(validator.isValid(6, 5));
    }

    @Test
    void freeShippingFromFiftyDollars() {
        assertEquals(0, validator.shippingCost(5000));
    }
}

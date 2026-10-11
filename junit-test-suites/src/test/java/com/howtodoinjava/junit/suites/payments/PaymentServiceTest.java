package com.howtodoinjava.junit.suites.payments;

import java.math.BigDecimal;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PaymentServiceTest {

    private final PaymentService payments = new PaymentService();

    @Test
    @Tag("fast")
    void rejectsShortCardNumber() {
        assertFalse(payments.isValidCard("1234"));
    }

    @Test
    void chargesValidCard() {
        assertEquals("CHARGED", payments.charge("4111111111111111", new BigDecimal("9.00")));
    }
}

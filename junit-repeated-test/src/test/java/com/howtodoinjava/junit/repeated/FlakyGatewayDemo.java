package com.howtodoinjava.junit.repeated;

import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Fails on purpose to show failureThreshold; not matched by Surefire's default includes.
// Run with: mvn test -Dtest=FlakyGatewayDemo
class FlakyGatewayDemo {

    private static final PaymentGateway gateway = new PaymentGateway();

    @RepeatedTest(value = 10, failureThreshold = 1)
    void chargesCard() {
        assertEquals("OK", gateway.charge(4_999));
    }
}

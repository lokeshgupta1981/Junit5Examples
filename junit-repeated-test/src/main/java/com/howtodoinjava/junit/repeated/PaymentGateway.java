package com.howtodoinjava.junit.repeated;

public class PaymentGateway {

    private int calls;

    // Simulates an unstable remote service: every third call times out
    public String charge(long amountInCents) {
        calls++;
        if (calls % 3 == 0) {
            return "TIMEOUT";
        }
        return amountInCents > 0 ? "OK" : "REJECTED";
    }
}

package com.howtodoinjava.junit.invoice;

public record Card(String lastDigits) implements PaymentMethod {
}

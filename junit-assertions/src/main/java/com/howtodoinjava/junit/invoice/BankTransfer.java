package com.howtodoinjava.junit.invoice;

public record BankTransfer(String iban) implements PaymentMethod {
}

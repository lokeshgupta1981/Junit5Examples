package com.howtodoinjava.junit.invoice;

public sealed interface PaymentMethod permits Card, BankTransfer {
}

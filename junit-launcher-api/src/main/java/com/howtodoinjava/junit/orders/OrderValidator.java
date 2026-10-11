package com.howtodoinjava.junit.orders;

public class OrderValidator {

    public boolean isValid(int quantity, int stock) {
        return quantity > 0 && quantity <= stock;
    }

    public int shippingCost(int totalCents) {
        return totalCents >= 5000 ? 0 : 499;
    }
}

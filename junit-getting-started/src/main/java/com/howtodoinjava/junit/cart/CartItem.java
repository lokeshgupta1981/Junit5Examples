package com.howtodoinjava.junit.cart;

import java.math.BigDecimal;

public record CartItem(String product, BigDecimal price, int quantity) {
}

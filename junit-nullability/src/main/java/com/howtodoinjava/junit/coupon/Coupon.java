package com.howtodoinjava.junit.coupon;

import org.jspecify.annotations.Nullable;

public record Coupon(String code, int percentOff, @Nullable String note) {
}

package com.howtodoinjava.junit.coupon;

import org.jspecify.annotations.Nullable;

import java.util.Map;

public class CouponService {

    private final Map<String, Coupon> coupons = Map.of(
            "WELCOME10", new Coupon("WELCOME10", 10, null),
            "SPRING25", new Coupon("SPRING25", 25, "Valid until May 31"));

    public @Nullable Coupon findByCode(String code) {
        return coupons.get(code.strip().toUpperCase());
    }

    public int discountedPrice(int price, String code) {
        Coupon coupon = findByCode(code);
        if (coupon == null) {
            return price;
        }
        return price - price * coupon.percentOff() / 100;
    }
}

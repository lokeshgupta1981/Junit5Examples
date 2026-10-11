package com.howtodoinjava.junit.coupon;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(TimingExtension.class)
class CouponServiceTest {

    private final CouponService service = new CouponService();

    @Test
    void findsCouponIgnoringCase() {
        Coupon coupon = service.findByCode(" welcome10 ");

        assertNotNull(coupon);
        assertEquals(10, coupon.percentOff());
    }

    @Test
    void unknownCodeReturnsNull() {
        assertNull(service.findByCode("NOPE"));
    }

    @Test
    void unknownCodeKeepsFullPrice() {
        assertEquals(200, service.discountedPrice(200, "NOPE"));
        assertEquals(150, service.discountedPrice(200, "spring25"));
    }
}

package com.howtodoinjava.junit.suites.payments.refunds;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("slow")
class RefundPolicyTest {

    @Test
    void allowsRefundWithin30Days() {
        assertTrue(new RefundPolicy().canRefund(30));
    }
}

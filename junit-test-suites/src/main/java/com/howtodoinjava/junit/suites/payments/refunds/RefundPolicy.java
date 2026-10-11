package com.howtodoinjava.junit.suites.payments.refunds;

public class RefundPolicy {

    public boolean canRefund(int daysSinceDelivery) {
        return daysSinceDelivery <= 30;
    }
}

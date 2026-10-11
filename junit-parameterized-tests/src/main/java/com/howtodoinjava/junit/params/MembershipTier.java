package com.howtodoinjava.junit.params;

public enum MembershipTier {

    BASIC(0), SILVER(5), GOLD(10);

    private final int discountPercent;

    MembershipTier(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int discountPercent() {
        return discountPercent;
    }
}

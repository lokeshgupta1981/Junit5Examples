package com.howtodoinjava.junit.params;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MembershipTierTest {

    @BeforeEach
    void printName(TestInfo info) {
        System.out.println(info.getDisplayName());
    }

    @ParameterizedTest
    @EnumSource(MembershipTier.class)
    void discountIsNeverAboveTenPercent(MembershipTier tier) {
        assertTrue(tier.discountPercent() <= 10);
    }

    @ParameterizedTest
    @EnumSource(names = {"SILVER", "GOLD"})
    void paidTiersGetADiscount(MembershipTier tier) {
        assertTrue(tier.discountPercent() > 0);
    }

    @ParameterizedTest
    @EnumSource(mode = EnumSource.Mode.EXCLUDE, names = "BASIC")
    void allTiersExceptBasicGetADiscount(MembershipTier tier) {
        assertTrue(tier.discountPercent() > 0);
    }
}

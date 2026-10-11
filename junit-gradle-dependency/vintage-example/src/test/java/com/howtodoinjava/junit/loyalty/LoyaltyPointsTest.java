package com.howtodoinjava.junit.loyalty;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LoyaltyPointsTest {

  @Test
  void membersEarnDoublePoints() {
    assertEquals(8, new LoyaltyPoints().forOrder(45, true));
  }
}

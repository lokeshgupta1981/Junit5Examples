package com.howtodoinjava.junit.loyalty;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LegacyLoyaltyTest {

  @Test
  public void smallOrderEarnsNoPoints() {
    assertEquals(0, new LoyaltyPoints().forOrder(9, false));
  }
}

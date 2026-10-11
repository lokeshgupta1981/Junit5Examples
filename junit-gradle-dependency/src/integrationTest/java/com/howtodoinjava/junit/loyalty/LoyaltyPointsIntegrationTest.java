package com.howtodoinjava.junit.loyalty;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class LoyaltyPointsIntegrationTest {

  @Test
  void pointsForAMonthOfOrders() {
    LoyaltyPoints loyalty = new LoyaltyPoints();
    int total = List.of(120, 35, 80).stream()
        .mapToInt(amount -> loyalty.forOrder(amount, true))
        .sum();
    assertEquals(46, total);
  }
}

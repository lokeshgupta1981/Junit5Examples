package com.howtodoinjava.junit.loyalty;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LoyaltyPointsTest {

  private final LoyaltyPoints loyalty = new LoyaltyPoints();

  @ParameterizedTest
  @CsvSource({"45, false, 4", "45, true, 8", "9, true, 0"})
  void pointsPerTenDollars(int total, boolean member, int expected) {
    assertEquals(expected, loyalty.forOrder(total, member));
  }

  @Test
  void rejectsNegativeTotal() {
    assertThrows(IllegalArgumentException.class, () -> loyalty.forOrder(-1, false));
  }

  @Tag("slow")
  @Test
  void largeOrderHistory() {
    int sum = 0;
    for (int i = 0; i < 1_000_000; i++) {
      sum += loyalty.forOrder(i % 500, i % 2 == 0);
    }
    assertEquals(36_750_000, sum);
  }
}

package com.howtodoinjava.junit.vintage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DiscountServiceTest {

  @Test
  void loyalCustomerGetsFifteenPercent() {
    assertEquals(15, new DiscountService().percentFor(6));
  }
}

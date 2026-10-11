package com.howtodoinjava.junit.shipping;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("Base rates are tested in subclasses")
class BaseRateTest {

  @Test
  void baseRate() {
    assertEquals(499, new ShippingCalculator().domesticRate(100));
  }
}

package com.howtodoinjava.junit.shipping;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("Express shipping is paused until the new carrier contract, see #150")
class ExpressShippingTest {

  @BeforeAll
  static void connectToCarrier() {
    System.out.println("@BeforeAll of ExpressShippingTest");
  }

  @Test
  void nextDayDelivery() {
    assertEquals(1299, 1299);
  }

  @Test
  void sameDayDelivery() {
    assertEquals(2499, 2499);
  }
}

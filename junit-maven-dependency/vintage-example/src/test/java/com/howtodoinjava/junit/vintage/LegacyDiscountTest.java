package com.howtodoinjava.junit.vintage;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LegacyDiscountTest {

  @Test
  public void newCustomerGetsNoDiscount() {
    assertEquals(0, new DiscountService().percentFor(0));
  }
}

package com.howtodoinjava.junit.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.howtodoinjava.junit.orders.OrderTotals;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

// Fails on purpose to show a failure in the report. Excluded from the default build.
class RefundRulesDemoTest {

  @Test
  void refundIncludesShipping() {
    BigDecimal refund = new OrderTotals().refund(new BigDecimal("35.00"), 1, 1);
    assertEquals(new BigDecimal("39.99"), refund);
  }

  @Test
  void refundForTwoOfThreeItems() {
    assertEquals(new BigDecimal("20.00"), new OrderTotals().refund(new BigDecimal("30.00"), 2, 3));
  }
}

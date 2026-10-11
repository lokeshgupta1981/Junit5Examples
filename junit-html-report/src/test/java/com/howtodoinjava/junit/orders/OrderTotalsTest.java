package com.howtodoinjava.junit.orders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTotalsTest {

  private final OrderTotals totals = new OrderTotals();

  @Test
  @DisplayName("Subtotal adds all item prices")
  void subtotalAddsPrices() {
    BigDecimal subtotal = totals.subtotal(List.of(new BigDecimal("12.50"), new BigDecimal("7.50")));
    assertEquals(new BigDecimal("20.00"), subtotal);
  }

  @Test
  @DisplayName("Tax is 8 percent, rounded to cents")
  void taxIsRounded() {
    assertEquals(new BigDecimal("1.64"), totals.tax(new BigDecimal("20.49")));
  }

  @Test
  @DisplayName("Refund is proportional to returned items")
  void partialRefund() {
    assertEquals(new BigDecimal("10.00"), totals.refund(new BigDecimal("30.00"), 1, 3));
  }

  @Test
  @DisplayName("Returning more items than ordered is rejected")
  void tooManyReturns() {
    assertThrows(IllegalArgumentException.class,
        () -> totals.refund(new BigDecimal("30.00"), 4, 3));
  }

  @Test
  @Disabled("Gift cards are not supported yet")
  void giftCardPayment() {
  }
}

package com.howtodoinjava.junit.orders;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class OrderTotals {

  private static final BigDecimal TAX_RATE = new BigDecimal("0.08");

  public BigDecimal subtotal(List<BigDecimal> prices) {
    return prices.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public BigDecimal tax(BigDecimal subtotal) {
    return subtotal.multiply(TAX_RATE).setScale(2, RoundingMode.HALF_UP);
  }

  public BigDecimal refund(BigDecimal paid, int returnedItems, int totalItems) {
    if (returnedItems > totalItems) {
      throw new IllegalArgumentException("Cannot return more items than ordered");
    }
    return paid.multiply(BigDecimal.valueOf(returnedItems))
        .divide(BigDecimal.valueOf(totalItems), 2, RoundingMode.HALF_UP);
  }
}

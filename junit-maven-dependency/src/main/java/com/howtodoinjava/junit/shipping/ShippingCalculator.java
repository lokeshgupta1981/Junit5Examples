package com.howtodoinjava.junit.shipping;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ShippingCalculator {

  private static final BigDecimal BASE_FEE = new BigDecimal("4.99");
  private static final BigDecimal PER_KG = new BigDecimal("1.50");
  private static final BigDecimal FREE_FROM = new BigDecimal("50.00");

  public BigDecimal cost(BigDecimal orderTotal, double weightKg) {
    if (weightKg <= 0) {
      throw new IllegalArgumentException("Weight must be positive: " + weightKg);
    }
    if (orderTotal.compareTo(FREE_FROM) >= 0) {
      return BigDecimal.ZERO.setScale(2);
    }
    BigDecimal weightFee = PER_KG.multiply(BigDecimal.valueOf(Math.ceil(weightKg)));
    return BASE_FEE.add(weightFee).setScale(2, RoundingMode.HALF_UP);
  }
}

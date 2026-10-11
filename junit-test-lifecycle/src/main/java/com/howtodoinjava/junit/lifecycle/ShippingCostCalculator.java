package com.howtodoinjava.junit.lifecycle;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Calculates the shipping cost of a parcel: a base fee plus a price per started kilogram.
 */
public class ShippingCostCalculator {

  private static final BigDecimal BASE_FEE = new BigDecimal("4.00");
  private static final BigDecimal PER_KG = new BigDecimal("1.50");

  public BigDecimal cost(double weightKg) {
    if (weightKg <= 0) {
      throw new IllegalArgumentException("Weight must be positive: " + weightKg);
    }
    BigDecimal kilos = BigDecimal.valueOf(weightKg).setScale(0, RoundingMode.CEILING);
    return BASE_FEE.add(PER_KG.multiply(kilos));
  }
}

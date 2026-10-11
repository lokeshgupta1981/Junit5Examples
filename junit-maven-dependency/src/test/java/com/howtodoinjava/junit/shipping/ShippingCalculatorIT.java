package com.howtodoinjava.junit.shipping;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

class ShippingCalculatorIT {

  @Test
  void costsForAWholeCart() {
    ShippingCalculator calculator = new ShippingCalculator();
    List<BigDecimal> costs = List.of(
        calculator.cost(new BigDecimal("12.00"), 0.8),
        calculator.cost(new BigDecimal("75.00"), 4.0));
    assertEquals(List.of(new BigDecimal("6.49"), new BigDecimal("0.00")), costs);
  }
}

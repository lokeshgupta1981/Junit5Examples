package com.howtodoinjava.junit.shipping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ShippingCalculatorTest {

  private final ShippingCalculator calculator = new ShippingCalculator();

  @Test
  void freeShippingFromFiftyDollars() {
    assertEquals(new BigDecimal("0.00"), calculator.cost(new BigDecimal("50.00"), 3.0));
  }

  @ParameterizedTest
  @CsvSource({"0.4, 6.49", "1.0, 6.49", "2.5, 9.49"})
  void chargesPerStartedKilogram(double weightKg, BigDecimal expected) {
    assertEquals(expected, calculator.cost(new BigDecimal("20.00"), weightKg));
  }

  @Test
  void rejectsZeroWeight() {
    assertThrows(IllegalArgumentException.class, () -> calculator.cost(BigDecimal.TEN, 0));
  }
}

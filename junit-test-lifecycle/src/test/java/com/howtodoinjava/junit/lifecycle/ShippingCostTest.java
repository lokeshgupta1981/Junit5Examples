package com.howtodoinjava.junit.lifecycle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The quick example: the four lifecycle methods and the constructor, with the default PER_METHOD lifecycle.
 */
class ShippingCostTest {

  ShippingCostCalculator calculator;

  ShippingCostTest() {
    System.out.println("  constructor");
  }

  @BeforeAll
  static void beforeAll() {
    System.out.println("@BeforeAll");
  }

  @BeforeEach
  void beforeEach() {
    calculator = new ShippingCostCalculator();
    System.out.println("  @BeforeEach");
  }

  @Test
  void costOfSmallParcel() {
    System.out.println("    @Test costOfSmallParcel");
    assertEquals(new BigDecimal("7.00"), calculator.cost(1.2));           // 4.00 + 2 x 1.50
  }

  @Test
  void rejectsZeroWeight() {
    System.out.println("    @Test rejectsZeroWeight");
    assertThrows(IllegalArgumentException.class, () -> calculator.cost(0));
  }

  @AfterEach
  void afterEach() {
    System.out.println("  @AfterEach");
  }

  @AfterAll
  static void afterAll() {
    System.out.println("@AfterAll");
  }
}

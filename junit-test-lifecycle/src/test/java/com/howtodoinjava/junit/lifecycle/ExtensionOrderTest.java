package com.howtodoinjava.junit.lifecycle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Prints the full order of extension callbacks and lifecycle methods for one test.
 */
@ExtendWith(LifecycleLogger.class)
class ExtensionOrderTest {

  ShippingCostCalculator calculator;

  ExtensionOrderTest() {
    System.out.println("    constructor");
  }

  @BeforeAll
  static void beforeAll() {
    System.out.println("@BeforeAll");
  }

  @BeforeEach
  void beforeEach() {
    calculator = new ShippingCostCalculator();
    System.out.println("    @BeforeEach");
  }

  @Test
  void costOfHeavyParcel() {
    System.out.println("      @Test costOfHeavyParcel");
    assertEquals(new BigDecimal("19.00"), calculator.cost(10));         // 4.00 + 10 x 1.50
  }

  @AfterEach
  void afterEach() {
    System.out.println("    @AfterEach");
  }

  @AfterAll
  static void afterAll() {
    System.out.println("@AfterAll");
  }
}

package com.howtodoinjava.junit.lifecycle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * The same lifecycle with PER_CLASS: the constructor runs once, before the non-static @BeforeAll.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PerClassShippingTest {

  ShippingCostCalculator calculator;

  PerClassShippingTest() {
    System.out.println("constructor");
  }

  @BeforeAll
  void beforeAll() {
    System.out.println("@BeforeAll (non-static)");
  }

  @BeforeEach
  void beforeEach() {
    calculator = new ShippingCostCalculator();
    System.out.println("  @BeforeEach");
  }

  @Test
  void costOfSmallParcel() {
    System.out.println("    @Test costOfSmallParcel");
    assertEquals(new BigDecimal("7.00"), calculator.cost(1.2));
  }

  @Test
  void costOfHeavyParcel() {
    System.out.println("    @Test costOfHeavyParcel");
    assertEquals(new BigDecimal("19.00"), calculator.cost(10));
  }

  @AfterEach
  void afterEach() {
    System.out.println("  @AfterEach");
  }

  @AfterAll
  void afterAll() {
    System.out.println("@AfterAll (non-static)");
  }
}

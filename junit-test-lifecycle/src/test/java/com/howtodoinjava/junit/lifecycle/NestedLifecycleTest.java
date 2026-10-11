package com.howtodoinjava.junit.lifecycle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * For a test in a @Nested class, the @BeforeEach methods of the outer class run first and its @AfterEach methods last.
 */
class NestedLifecycleTest {

  ShippingCostCalculator calculator;

  @BeforeEach
  void createCalculator() {
    calculator = new ShippingCostCalculator();
    System.out.println("outer @BeforeEach");
  }

  @AfterEach
  void outerAfterEach() {
    System.out.println("outer @AfterEach");
  }

  @Nested
  class ExpressParcels {

    BigDecimal surcharge;

    @BeforeEach
    void addSurcharge() {
      surcharge = new BigDecimal("5.00");               // calculator is already set
      System.out.println("  inner @BeforeEach");
    }

    @Test
    void expressCost() {
      System.out.println("    @Test expressCost");
      assertEquals(new BigDecimal("12.00"), calculator.cost(2).add(surcharge));
    }

    @AfterEach
    void innerAfterEach() {
      System.out.println("  inner @AfterEach");
    }
  }
}

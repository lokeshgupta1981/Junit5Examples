package com.howtodoinjava.junit.shipping;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

class ShippingCalculatorTest {

  private final ShippingCalculator calculator = new ShippingCalculator();

  ShippingCalculatorTest() {
    System.out.println("constructor");
  }

  @BeforeAll
  static void beforeAll() {
    System.out.println("@BeforeAll");
  }

  @BeforeEach
  void beforeEach(TestInfo info) {
    System.out.println("@BeforeEach for " + info.getDisplayName());
  }

  @Test
  void domesticParcelUpToOneKilo() {
    assertEquals(499, calculator.domesticRate(800));
  }

  @Test
  @Disabled("Bug #142: non-EU surcharge is not implemented yet")
  void internationalParcelToCanada() {
    assertEquals(1797, calculator.internationalRate(800, "CA"));
  }

  @AfterEach
  void afterEach(TestInfo info) {
    System.out.println("@AfterEach for " + info.getDisplayName());
  }

  @AfterAll
  static void afterAll() {
    System.out.println("@AfterAll");
  }
}

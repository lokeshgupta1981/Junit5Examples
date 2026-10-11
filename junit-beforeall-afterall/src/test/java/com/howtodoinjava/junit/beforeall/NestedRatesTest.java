package com.howtodoinjava.junit.beforeall;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Each @Nested class gets its own @BeforeAll and @AfterAll. Static methods in inner classes need Java 16 or later.
 */
class NestedRatesTest {

  @BeforeAll
  static void loadCurrencies() {
    System.out.println("@BeforeAll outer: load currencies");
  }

  @AfterAll
  static void clearCurrencies() {
    System.out.println("@AfterAll outer");
  }

  @Test
  void outerTest() {
    System.out.println("  @Test outerTest");
    assertTrue(true);
  }

  @Nested
  class HistoricalRates {

    @BeforeAll
    static void loadHistory() {                     // static in an inner class, Java 16+
      System.out.println("  @BeforeAll nested: load 2025 history");
    }

    @AfterAll
    static void clearHistory() {
      System.out.println("  @AfterAll nested");
    }

    @Test
    void nestedTest() {
      System.out.println("    @Test nestedTest");
      assertTrue(true);
    }
  }
}

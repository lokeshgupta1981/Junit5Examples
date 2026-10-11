package com.howtodoinjava.junit.beforeall.demo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

/**
 * Fails on purpose: both tests pass, but the exception in @AfterAll fails the test class.
 * Run with: mvn test -Dtest=FailingAfterAllDemo
 */
class FailingAfterAllDemo {

  @Test
  void convertsUsdToEur() {
    System.out.println("@Test convertsUsdToEur passed");
  }

  @AfterAll
  static void deleteRateFiles() {
    throw new IllegalStateException("Cannot delete rates-2026.csv");
  }
}

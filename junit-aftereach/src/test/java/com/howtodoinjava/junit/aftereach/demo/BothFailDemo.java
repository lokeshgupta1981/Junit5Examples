package com.howtodoinjava.junit.aftereach.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/**
 * Fails on purpose: the test and @AfterEach both throw; the cleanup exception is added as suppressed.
 * Run with: mvn test -Dtest=BothFailDemo
 */
class BothFailDemo {

  @Test
  void cachesReport() {
    assertEquals(2, 1, "report count");
  }

  @AfterEach
  void deleteReportFile() {
    throw new IllegalStateException("Cannot delete report file march.pdf");
  }
}

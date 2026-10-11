package com.howtodoinjava.junit.aftereach.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.howtodoinjava.junit.aftereach.ReportCache;
import org.junit.jupiter.api.Test;

/**
 * Fails on purpose: without @AfterEach, the second test sees the report that the first test cached.
 * Run with: mvn test -Dtest=NoCleanupDemo
 */
class NoCleanupDemo {

  @Test
  void cachesMonthlyReport() {
    ReportCache.put("march", "total 120");
    System.out.println("@Test cachesMonthlyReport: size " + ReportCache.size());
    assertEquals(1, ReportCache.size());
  }

  @Test
  void cachesYearlyReport() {
    ReportCache.put("2026", "total 1450");
    System.out.println("@Test cachesYearlyReport: size " + ReportCache.size());
    assertEquals(1, ReportCache.size());
  }
}

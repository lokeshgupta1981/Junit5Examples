package com.howtodoinjava.junit.aftereach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/**
 * The quick example: @AfterEach clears the static cache after every test, so each test starts with an empty cache.
 */
class ReportCacheTest {

  @AfterEach
  void clearCache() {
    ReportCache.clear();
    System.out.println("@AfterEach: cache cleared");
  }

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

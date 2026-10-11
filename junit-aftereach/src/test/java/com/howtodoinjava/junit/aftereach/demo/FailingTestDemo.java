package com.howtodoinjava.junit.aftereach.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.howtodoinjava.junit.aftereach.ReportCache;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/**
 * Fails on purpose: the assertion fails, and @AfterEach still clears the cache.
 * Run with: mvn test -Dtest=FailingTestDemo
 */
class FailingTestDemo {

  @Test
  void cachesReport() {
    ReportCache.put("march", "total 120");
    assertEquals(2, ReportCache.size(), "report count");
  }

  @AfterEach
  void clearCache() {
    ReportCache.clear();
    System.out.println("@AfterEach clearCache() ran, size " + ReportCache.size());
  }
}

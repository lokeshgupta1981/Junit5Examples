package com.howtodoinjava.junit.aftereach.demo;

import com.howtodoinjava.junit.aftereach.ReportCache;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/**
 * Fails on purpose: the test passes, but @AfterEach throws, so JUnit reports the test as failed.
 * Run with: mvn test -Dtest=FailingCleanupDemo
 */
class FailingCleanupDemo {

  @Test
  void cachesReport() {
    ReportCache.put("march", "total 120");
    System.out.println("@Test cachesReport passed");
  }

  @AfterEach
  void deleteReportFile() {
    ReportCache.clear();
    throw new IllegalStateException("Cannot delete report file march.pdf");
  }
}

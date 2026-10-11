package com.howtodoinjava.junit.aftereach.demo;

import com.howtodoinjava.junit.aftereach.ReportCache;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/**
 * Invalid on purpose: @AfterEach must not be static.
 * Run with: mvn test -Dtest=StaticAfterEachDemo
 */
class StaticAfterEachDemo {

  @Test
  void cachesReport() {
    ReportCache.put("march", "total 120");
  }

  @AfterEach
  static void clearCache() {
    ReportCache.clear();
  }
}

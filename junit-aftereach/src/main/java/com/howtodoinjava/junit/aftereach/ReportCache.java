package com.howtodoinjava.junit.aftereach;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * An app-wide cache of generated reports. The map is static, so it survives from one test instance to the next.
 */
public final class ReportCache {

  private static final Map<String, String> REPORTS = new ConcurrentHashMap<>();

  private ReportCache() {
  }

  public static void put(String key, String report) {
    REPORTS.put(key, report);
  }

  public static int size() {
    return REPORTS.size();
  }

  public static void clear() {
    REPORTS.clear();
  }
}

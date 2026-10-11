package com.howtodoinjava.junit.autoclose;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;

/**
 * Instance fields close after each test (after @AfterEach), static fields after all tests (after @AfterAll).
 */
class CloseTimingTest {

  @AutoClose
  AuditLog perTestLog = new AuditLog("instance field");

  @AutoClose
  static AuditLog sharedLog = new AuditLog("static field");

  @Test
  void placesOrder() {
    System.out.println("@Test placesOrder");
  }

  @Test
  void cancelsOrder() {
    System.out.println("@Test cancelsOrder");
  }

  @AfterEach
  void afterEach() {
    System.out.println("@AfterEach");
  }

  @AfterAll
  static void afterAll() {
    System.out.println("@AfterAll");
  }
}

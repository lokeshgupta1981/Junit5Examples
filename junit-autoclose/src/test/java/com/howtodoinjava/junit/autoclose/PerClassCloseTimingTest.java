package com.howtodoinjava.junit.autoclose;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * With PER_CLASS, an instance field closes only once, after @AfterAll and after the static fields.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PerClassCloseTimingTest {

  @AutoClose
  AuditLog perInstanceLog = new AuditLog("instance field (PER_CLASS)");

  @AutoClose
  static AuditLog sharedLog = new AuditLog("static field (PER_CLASS)");

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
  void afterAll() {
    System.out.println("@AfterAll");
  }
}

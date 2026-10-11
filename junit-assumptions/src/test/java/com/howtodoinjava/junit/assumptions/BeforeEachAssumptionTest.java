package com.howtodoinjava.junit.assumptions;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/** A failed assumption in @BeforeEach aborts each test separately. @AfterEach still runs. */
class BeforeEachAssumptionTest {

  @BeforeEach
  void checkDatabase(TestInfo info) {
    assumeTrue(System.getProperty("test.db.url") != null,
        () -> "test.db.url is not set, skipping " + info.getDisplayName());
  }

  @Test
  void savesOrder() {
    System.out.println("savesOrder");
  }

  @Test
  void deletesOrder() {
    System.out.println("deletesOrder");
  }

  @AfterEach
  void cleanUp(TestInfo info) {
    System.out.println("@AfterEach runs for " + info.getDisplayName());
  }
}

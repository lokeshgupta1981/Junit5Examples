package com.howtodoinjava.junit.aftereach.demo;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * An aborted test (failed assumption) still runs @AfterEach. A disabled test never starts, so @AfterEach does not run.
 * Run with: mvn test -Dtest=SkippedTestsDemo
 */
class SkippedTestsDemo {

  @Test
  void needsMailServer() {
    assumeTrue(System.getenv("MAIL_HOST") != null, "MAIL_HOST is not set");
    System.out.println("@Test needsMailServer");
  }

  @Disabled("Report format changes in the next release")
  @Test
  void exportsPdf() {
    System.out.println("@Test exportsPdf");
  }

  @AfterEach
  void cleanUp(TestInfo testInfo) {
    System.out.println("@AfterEach after " + testInfo.getDisplayName());
  }
}

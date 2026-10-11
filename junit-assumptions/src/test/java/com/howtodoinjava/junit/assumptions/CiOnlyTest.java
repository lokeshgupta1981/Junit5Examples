package com.howtodoinjava.junit.assumptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.Test;

/**
 * Runs only on a CI server. GitHub Actions and GitLab CI set the CI variable to "true".
 * Locally the test is aborted and Maven reports it as skipped.
 */
class CiOnlyTest {

  @Test
  void publishesReportOnCi() {
    assumeTrue(System.getenv("CI") != null, "Not running on CI");   // aborts here on a laptop

    assertEquals("true", System.getenv("CI"));                      // runs only on CI
  }
}

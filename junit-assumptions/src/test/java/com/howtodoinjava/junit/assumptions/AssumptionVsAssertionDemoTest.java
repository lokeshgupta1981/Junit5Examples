package com.howtodoinjava.junit.assumptions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Same condition, two outcomes: the assumption aborts the test, the assertion fails it.
 * Tagged "demo" so the normal build does not fail. Run it with:
 * mvn test -Dtest=AssumptionVsAssertionDemoTest -DexcludedGroups=none
 */
@Tag("demo")
class AssumptionVsAssertionDemoTest {

  boolean databaseConfigured = System.getProperty("test.db.url") != null;

  @Test
  void withAssumption() {
    assumeTrue(databaseConfigured, "test.db.url is not set");   // aborted (skipped)
  }

  @Test
  void withAssertion() {
    assertTrue(databaseConfigured, "test.db.url is not set");   // failed
  }
}

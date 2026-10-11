package com.howtodoinjava.junit.assumptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/** In a parameterized test, a failed assumption aborts only that one invocation. */
class ParameterizedAssumptionTest {

  private final ReportFormatter formatter = new ReportFormatter();

  @ParameterizedTest
  @ValueSource(strings = {"apple", "", "banana"})
  void formatsHeader(String title) {
    assumeFalse(title.isBlank(), "Empty title");    // aborts the "" invocation only

    assertEquals("== " + title.toUpperCase() + " ==", formatter.header(title));
  }
}

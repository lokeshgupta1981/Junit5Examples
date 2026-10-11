package com.howtodoinjava.junit.assumptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

import org.junit.jupiter.api.Test;

/** assumeFalse(): the test continues only when the condition is false. */
class AssumeFalseTest {

  @Test
  void skipsOnWindows() {
    assumeFalse(System.getProperty("os.name").startsWith("Windows"), "Uses POSIX paths");

    assertEquals("/", System.getProperty("file.separator"));
  }

  @Test
  void skipsOnCi() {
    assumeFalse(System.getenv("CI") != null, "Needs a local browser");

    assertNotNull(System.getProperty("user.home"));   // the browser test code goes here
  }
}

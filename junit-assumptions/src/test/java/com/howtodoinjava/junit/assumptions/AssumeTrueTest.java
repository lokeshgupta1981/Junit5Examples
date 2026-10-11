package com.howtodoinjava.junit.assumptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.Test;

/** assumeTrue(): the test continues only when the condition is true. */
class AssumeTrueTest {

  @Test
  void runsOnLinux() {
    assumeTrue(System.getProperty("os.name").startsWith("Linux"));          // true on Linux: continues

    assertEquals("\n", System.lineSeparator());
  }

  @Test
  void runsWhenDatabaseUrlIsSet() {
    assumeTrue(System.getProperty("test.db.url") != null, "test.db.url is not set");   // aborted

    assertTrue(System.getProperty("test.db.url").startsWith("jdbc:"));
  }

  @Test
  void runsOnJava25OrNewer() {
    assumeTrue(() -> Runtime.version().feature() >= 25,                     // BooleanSupplier
        () -> "Needs Java 25, found " + Runtime.version());                  // message built only on abort

    assertTrue(Runtime.version().feature() >= 25);
  }
}

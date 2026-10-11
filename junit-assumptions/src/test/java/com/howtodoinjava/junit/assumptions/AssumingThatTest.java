package com.howtodoinjava.junit.assumptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.util.List;
import org.junit.jupiter.api.Test;

/** assumingThat(): runs a block only when the condition is true; the rest of the test always runs. */
class AssumingThatTest {

  private final ReportFormatter formatter = new ReportFormatter();

  @Test
  void joinsLinesForCurrentOs() {
    boolean windows = System.getProperty("os.name").startsWith("Windows");
    String report = formatter.join(List.of("apple", "banana"));

    assumingThat(windows, () -> assertEquals("apple\r\nbanana", report));     // runs on Windows only
    assumingThat(!windows, () -> assertEquals("apple\nbanana", report));      // runs on Linux and macOS

    assertEquals("== FRUITS ==", formatter.header("fruits"));                 // runs everywhere
  }
}

package com.howtodoinjava.junit.assumptions;

import java.util.List;

/**
 * Joins report lines with the line separator of the current operating system.
 * The tests use it to show OS-dependent assertions with assumingThat().
 */
public class ReportFormatter {

  public String join(List<String> lines) {
    return String.join(System.lineSeparator(), lines);
  }

  public String header(String title) {
    return "== " + title.toUpperCase() + " ==";
  }
}

package com.howtodoinjava.recipes;

/** A real helper class with no dependencies. Tests use it as a @Spy. */
public class TimeFormatter {

  public String format(int minutes) {
    int hours = minutes / 60;
    int rest = minutes % 60;
    if (hours == 0) {
      return rest + " min";
    }
    return rest == 0 ? hours + " h" : hours + " h " + rest + " min";
  }
}

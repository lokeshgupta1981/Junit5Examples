package com.howtodoinjava.junit.playlist;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

// Fails on purpose to show the failure messages. Not matched by the default Surefire includes;
// run it with: mvn test -Dtest=AssertThrowsFailureDemo
class AssertThrowsFailureDemo {

  @Test
  void nothingThrown() {
    NumberFormatException thrown = assertThrows(NumberFormatException.class, () -> Integer.parseInt("1"),
        "NumberFormatException error was expected");
  }

  @Test
  void wrongType() {
    NullPointerException thrown = assertThrows(NullPointerException.class, () -> Integer.parseInt("One"));
  }

  @Test
  void subclassIsNotExact() {
    IllegalArgumentException thrown = assertThrowsExactly(IllegalArgumentException.class,
        () -> Integer.parseInt("One"));
  }

  @Test
  void unexpectedException() {
    int seconds = assertDoesNotThrow(() -> Playlist.parseDuration("x:45"));
  }

  @Test
  void assertjMessageTypo() {
    assertThatThrownBy(() -> Playlist.parseDuration("x:45"))
        .hasMessage("Invalid duration: x45");
  }
}

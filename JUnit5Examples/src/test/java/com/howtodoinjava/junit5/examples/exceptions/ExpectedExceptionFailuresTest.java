package com.howtodoinjava.junit5.examples.exceptions;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Every test here fails on purpose to show the failure messages from the article.
 * Run them with:
 * mvn test -Dtest=ExpectedExceptionFailuresTest -Djunit.jupiter.conditions.deactivate=org.junit.*DisabledCondition
 */
@Disabled("Fails on purpose to show the failure messages")
class ExpectedExceptionFailuresTest {

  @Test
  void noExceptionThrown() {
    NumberFormatException thrown = assertThrows(NumberFormatException.class, () -> Integer.parseInt("1"),
        "NumberFormatException error was expected");
  }

  @Test
  void differentExceptionThrown() {
    NullPointerException thrown = assertThrows(NullPointerException.class, () -> Integer.parseInt("One"));
  }

  @Test
  void subclassThrownForExactMatch() {
    IllegalArgumentException thrown = assertThrowsExactly(IllegalArgumentException.class,
        () -> Integer.parseInt("One"));
  }

  @Test
  void exceptionThrownWhenNoneExpected() {
    int seconds = assertDoesNotThrow(() -> Playlist.parseDuration("x:45"));
  }

  @Test
  void assertJWrongMessage() {
    assertThatThrownBy(() -> Playlist.parseDuration("x:45"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Invalid duration: x45");
  }
}

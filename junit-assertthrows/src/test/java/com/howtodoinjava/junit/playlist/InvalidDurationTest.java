package com.howtodoinjava.junit.playlist;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class InvalidDurationTest {

  @ParameterizedTest
  @ValueSource(strings = {"3", "3:4:5", "x:45", "3:60", "-1:30"})
  void invalidDurationsAreRejected(String text) {
    IllegalArgumentException invalid = assertThrows(IllegalArgumentException.class,
        () -> Playlist.parseDuration(text));
    assertEquals("Invalid duration: " + text, invalid.getMessage());
  }

  @ParameterizedTest(name = "{0} -> {1}")
  @MethodSource("badDurations")
  void eachBadInputThrowsItsOwnException(String text, Class<? extends Exception> expectedType, String expectedMessage) {
    Exception thrown = assertThrowsExactly(expectedType, () -> Playlist.parseDuration(text));
    assertEquals(expectedMessage, thrown.getMessage());
  }

  static Stream<Arguments> badDurations() {
    return Stream.of(
        Arguments.of(null, NullPointerException.class, "duration must not be null"),
        Arguments.of("3", IllegalArgumentException.class, "Invalid duration: 3"),
        Arguments.of("x:45", IllegalArgumentException.class, "Invalid duration: x:45"));
  }

  @ParameterizedTest
  @CsvSource({"3:45, 225", "0:59, 59", "10:00, 600"})
  void validDurationsAreParsed(String text, int expectedSeconds) {
    int seconds = assertDoesNotThrow(() -> Playlist.parseDuration(text));
    assertEquals(expectedSeconds, seconds);
  }
}

package com.howtodoinjava.junit5.examples;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.howtodoinjava.junit5.examples.exceptions.Playlist;
import com.howtodoinjava.junit5.examples.exceptions.PlaylistException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.suite.api.SelectPackages;

/**
 * Source code for the article https://howtodoinjava.com/junit5/expected-exception-example/
 * Runs on JUnit 6.1.3. The same code compiles and passes on JUnit 5.8 or later.
 */
@SelectPackages("com.howtodoinjava.junit5.examples")
public class ExpectedExceptionTest {

  @Test
  void quickReference() {
    // 1. assertThrows() passes for the type or a subclass, and returns the exception
    NumberFormatException thrown = assertThrows(NumberFormatException.class, () -> Integer.parseInt("One"));
    String message = thrown.getMessage();                                       // For input string: "One"

    // 2. assertThrowsExactly() passes only for the exact type
    NumberFormatException exact = assertThrowsExactly(NumberFormatException.class, () -> Integer.parseInt("One"));

    // 3. assertDoesNotThrow() passes when nothing is thrown, and returns the lambda's result
    int number = assertDoesNotThrow(() -> Integer.parseInt("42"));             // 42

    assertEquals("For input string: \"One\"", message);
    assertEquals(thrown.getMessage(), exact.getMessage());
    assertEquals(42, number);
  }

  @Test
  void testExpectedException() {

    NumberFormatException thrown = assertThrows(NumberFormatException.class, () -> {
      int number = Integer.parseInt("One");
    }, "NumberFormatException was expected");
    String message = thrown.getMessage();                       // For input string: "One"

    Assertions.assertEquals("For input string: \"One\"", message);
  }

  @Test
  void testExpectedExceptionWithParentType() {

    IllegalArgumentException parent = assertThrows(IllegalArgumentException.class, () -> {
      int number = Integer.parseInt("One");
    });                                                         // passes, NumberFormatException is a subclass

    assertInstanceOf(NumberFormatException.class, parent);
  }

  @Test
  void anyExceptionPassesWithExceptionClass() {
    Exception any = assertThrows(Exception.class, () -> Integer.parseInt("One"));   // passes, too broad
    assertInstanceOf(NumberFormatException.class, any);
  }

  @Test
  void checkMessageOfReturnedException() {
    Playlist playlist = new Playlist();

    IllegalArgumentException blank = assertThrows(IllegalArgumentException.class, () -> playlist.add("  "));
    NullPointerException missing = assertThrows(NullPointerException.class, () -> playlist.add(null));

    String blankMessage = blank.getMessage();                         // title must not be blank
    String missingMessage = missing.getMessage();                     // title must not be null
    boolean mentionsBlank = blank.getMessage().contains("blank");     // true

    assertEquals("title must not be blank", blankMessage);
    assertEquals("title must not be null", missingMessage);
    assertTrue(mentionsBlank);
  }

  @Test
  void checkCauseOfReturnedException() {
    IllegalArgumentException invalid = assertThrows(IllegalArgumentException.class,
        () -> Playlist.parseDuration("x:45"));

    NumberFormatException cause = assertInstanceOf(NumberFormatException.class, invalid.getCause());

    String message = invalid.getMessage();                            // Invalid duration: x:45
    String causeMessage = cause.getMessage();                         // For input string: "x"

    assertEquals("Invalid duration: x:45", message);
    assertEquals("For input string: \"x\"", causeMessage);
  }

  @Test
  void checkedExceptionNeedsNoTryCatch() {
    Path missingFile = Path.of("missing-playlist.txt");

    PlaylistException failed = assertThrows(PlaylistException.class, () -> Playlist.load(missingFile));
    NoSuchFileException cause = assertInstanceOf(NoSuchFileException.class, failed.getCause());

    String message = failed.getMessage();                             // Cannot load playlist missing-playlist.txt
    String causeMessage = cause.getMessage();                         // missing-playlist.txt

    assertEquals("Cannot load playlist missing-playlist.txt", message);
    assertEquals("missing-playlist.txt", causeMessage);
  }

  @Test
  void assertThrowsExactlyMatchesOnlyTheExactType() {
    // IllegalArgumentException is thrown as is, so the exact match passes
    IllegalArgumentException wrongFormat = assertThrowsExactly(IllegalArgumentException.class,
        () -> Playlist.parseDuration("3"));
    String message = wrongFormat.getMessage();                        // Invalid duration: 3

    assertEquals("Invalid duration: 3", message);
  }

  @Test
  void assertDoesNotThrowReturnsTheValue() {
    Playlist playlist = new Playlist();

    int seconds = assertDoesNotThrow(() -> Playlist.parseDuration("3:45"));   // 225
    int size = assertDoesNotThrow(() -> playlist.add("Yesterday"), "valid titles are accepted");   // 1

    assertEquals(225, seconds);
    assertEquals(1, size);
  }

  @Test
  void keepOnlyTheThrowingCallInsideTheLambda() {
    // Setup runs outside the lambda, so only add() can satisfy the assertion
    Playlist playlist = new Playlist();
    int added = playlist.add("Hey Jude");                          // 1

    IllegalArgumentException blank = assertThrows(IllegalArgumentException.class, () -> playlist.add(""));

    String message = blank.getMessage();                              // title must not be blank
    int size = playlist.songs().size();                               // 1

    assertEquals("title must not be blank", message);
    assertEquals(1, added);
    assertEquals(1, size);
  }

  // ---------- Exceptions in parameterized tests ----------

  @ParameterizedTest
  @ValueSource(strings = {"3", "3:4:5", "x:45", "3:60", "-1:30"})
  void invalidDurationsAreRejected(String text) {
    IllegalArgumentException invalid = assertThrows(IllegalArgumentException.class,
        () -> Playlist.parseDuration(text));
    assertEquals("Invalid duration: " + text, invalid.getMessage());
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"   "})
  void missingTitlesAreRejected(String title) {
    Playlist playlist = new Playlist();
    RuntimeException rejected = assertThrows(RuntimeException.class, () -> playlist.add(title));
    assertTrue(rejected instanceof NullPointerException || rejected instanceof IllegalArgumentException);
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

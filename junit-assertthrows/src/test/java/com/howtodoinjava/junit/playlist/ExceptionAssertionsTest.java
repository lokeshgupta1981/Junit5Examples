package com.howtodoinjava.junit.playlist;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class ExceptionAssertionsTest {

  @Test
  void quickReference() {
    // 1. assertThrows() passes for the type or a subclass, and returns the exception
    NumberFormatException thrown = assertThrows(NumberFormatException.class, () -> Integer.parseInt("One"));
    String message = thrown.getMessage();

    // 2. assertThrowsExactly() passes only for the exact type
    NumberFormatException exact = assertThrowsExactly(NumberFormatException.class, () -> Integer.parseInt("One"));

    // 3. assertDoesNotThrow() passes when nothing is thrown, and returns the lambda's result
    int number = assertDoesNotThrow(() -> Integer.parseInt("42"));

    assertEquals("For input string: \"One\"", message);
    assertEquals("For input string: \"One\"", exact.getMessage());
    assertEquals(42, number);
  }

  @Test
  void subclassMatchesParentType() {
    NumberFormatException thrown = assertThrows(NumberFormatException.class, () -> {
      int number = Integer.parseInt("One");
    }, "NumberFormatException was expected");
    String message = thrown.getMessage();

    IllegalArgumentException parent = assertThrows(IllegalArgumentException.class, () -> {
      int number = Integer.parseInt("One");
    });

    assertEquals("For input string: \"One\"", message);
    assertInstanceOf(NumberFormatException.class, parent);
  }

  @Test
  void messagesOfInvalidTitles() {
    Playlist playlist = new Playlist();

    IllegalArgumentException blank = assertThrows(IllegalArgumentException.class, () -> playlist.add("  "));
    NullPointerException missing = assertThrows(NullPointerException.class, () -> playlist.add(null));

    String blankMessage = blank.getMessage();
    String missingMessage = missing.getMessage();
    boolean mentionsBlank = blank.getMessage().contains("blank");

    assertEquals("title must not be blank", blankMessage);
    assertEquals("title must not be null", missingMessage);
    assertTrue(mentionsBlank);
  }

  @Test
  void causeOfInvalidDuration() {
    IllegalArgumentException invalid = assertThrows(IllegalArgumentException.class,
        () -> Playlist.parseDuration("x:45"));
    NumberFormatException cause = assertInstanceOf(NumberFormatException.class, invalid.getCause());

    String message = invalid.getMessage();
    String causeMessage = cause.getMessage();

    assertEquals("Invalid duration: x:45", message);
    assertEquals("For input string: \"x\"", causeMessage);
  }

  @Test
  void checkedExceptionFromMissingFile() {
    Path missingFile = Path.of("missing-playlist.txt");

    PlaylistException failed = assertThrows(PlaylistException.class, () -> Playlist.load(missingFile));
    NoSuchFileException cause = assertInstanceOf(NoSuchFileException.class, failed.getCause());

    String message = failed.getMessage();
    String causeMessage = cause.getMessage();

    assertEquals("Cannot load playlist missing-playlist.txt", message);
    assertEquals("missing-playlist.txt", causeMessage);
  }

  @Test
  void onlyTheThrowingCallInsideTheLambda() {
    Playlist playlist = new Playlist();
    int added = playlist.add("Hey Jude");

    IllegalArgumentException blank = assertThrows(IllegalArgumentException.class, () -> playlist.add(""));

    String message = blank.getMessage();
    int size = playlist.songs().size();

    assertEquals(1, added);
    assertEquals("title must not be blank", message);
    assertEquals(1, size);
  }

  @Test
  void exactTypeOnly() {
    IllegalArgumentException wrongFormat = assertThrowsExactly(IllegalArgumentException.class,
        () -> Playlist.parseDuration("3"));
    String message = wrongFormat.getMessage();

    assertEquals("Invalid duration: 3", message);
  }

  @Test
  void validInputsDoNotThrow() {
    Playlist playlist = new Playlist();

    int seconds = assertDoesNotThrow(() -> Playlist.parseDuration("3:45"));
    int size = assertDoesNotThrow(() -> playlist.add("Yesterday"), "valid titles are accepted");

    assertEquals(225, seconds);
    assertEquals(1, size);
  }
}

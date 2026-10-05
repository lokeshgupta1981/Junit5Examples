package com.howtodoinjava.junit5.examples.exceptions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

import org.junit.jupiter.api.Test;

/**
 * The same checks as ExpectedExceptionTest, written with AssertJ 3.27.7.
 */
class AssertJExceptionTest {

  @Test
  void assertThatThrownByChecksTypeMessageAndCause() {
    assertThatThrownBy(() -> Playlist.parseDuration("x:45"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Invalid duration: x:45")
        .hasCauseInstanceOf(NumberFormatException.class)
        .hasRootCauseMessage("For input string: \"x\"");

    assertThatThrownBy(() -> Playlist.parseDuration("x:45"))
        .isExactlyInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("x:45");
  }

  @Test
  void assertThatExceptionOfTypeStartsWithTheType() {
    Playlist playlist = new Playlist();

    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> playlist.add(" "))
        .withMessage("title must not be blank");

    assertThatIllegalArgumentException()
        .isThrownBy(() -> Playlist.parseDuration("3:60"))
        .withMessageContaining("3:60");
  }

  @Test
  void catchThrowableOfTypeReturnsTheException() {
    IllegalArgumentException invalid = catchThrowableOfType(IllegalArgumentException.class,
        () -> Playlist.parseDuration("x:45"));

    assertThat(invalid).hasMessage("Invalid duration: x:45");
  }

  @Test
  void assertThatCodeChecksNothingIsThrown() {
    assertThatCode(() -> Playlist.parseDuration("3:45")).doesNotThrowAnyException();
  }
}

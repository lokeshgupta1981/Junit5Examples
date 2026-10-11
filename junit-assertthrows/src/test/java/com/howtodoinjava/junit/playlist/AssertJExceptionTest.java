package com.howtodoinjava.junit.playlist;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

import org.junit.jupiter.api.Test;

class AssertJExceptionTest {

  @Test
  void fluentExceptionChecks() {
    Playlist playlist = new Playlist();

    assertThatThrownBy(() -> Playlist.parseDuration("x:45"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Invalid duration: x:45")
        .hasCauseInstanceOf(NumberFormatException.class)
        .hasRootCauseMessage("For input string: \"x\"");

    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> playlist.add(" "))
        .withMessage("title must not be blank");

    IllegalArgumentException invalid = catchThrowableOfType(IllegalArgumentException.class,
        () -> Playlist.parseDuration("x:45"));

    assertThatCode(() -> Playlist.parseDuration("3:45")).doesNotThrowAnyException();

    assertThat(invalid).hasMessage("Invalid duration: x:45");
  }
}

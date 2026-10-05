package com.howtodoinjava.junit5.examples.exceptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A small playlist used as the code under test in ExpectedExceptionTest.
 */
public class Playlist {

  private final List<String> songs = new ArrayList<>();

  /**
   * Adds a song and returns the new size of the playlist.
   */
  public int add(String title) {
    Objects.requireNonNull(title, "title must not be null");
    if (title.isBlank()) {
      throw new IllegalArgumentException("title must not be blank");
    }
    songs.add(title.strip());
    return songs.size();
  }

  public List<String> songs() {
    return List.copyOf(songs);
  }

  /**
   * Parses a duration such as "3:45" into seconds (225).
   */
  public static int parseDuration(String text) {
    Objects.requireNonNull(text, "duration must not be null");
    String[] parts = text.strip().split(":");
    if (parts.length != 2) {
      throw new IllegalArgumentException("Invalid duration: " + text);
    }
    int minutes;
    int seconds;
    try {
      minutes = Integer.parseInt(parts[0]);
      seconds = Integer.parseInt(parts[1]);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid duration: " + text, e);
    }
    if (minutes < 0 || seconds < 0 || seconds > 59) {
      throw new IllegalArgumentException("Invalid duration: " + text);
    }
    return minutes * 60 + seconds;
  }

  /**
   * Reads one song title per line. Wraps any IOException in a checked PlaylistException.
   */
  public static Playlist load(Path file) throws PlaylistException {
    try {
      Playlist playlist = new Playlist();
      for (String line : Files.readAllLines(file)) {
        if (!line.isBlank()) {
          playlist.add(line);
        }
      }
      return playlist;
    } catch (IOException e) {
      throw new PlaylistException("Cannot load playlist " + file.getFileName(), e);
    }
  }
}

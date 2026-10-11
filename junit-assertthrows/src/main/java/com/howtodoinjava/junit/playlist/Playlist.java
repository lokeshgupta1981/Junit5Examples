package com.howtodoinjava.junit.playlist;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Playlist {

  private final List<String> songs = new ArrayList<>();

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

  public static int parseDuration(String text) {
    Objects.requireNonNull(text, "duration must not be null");
    String[] parts = text.split(":");
    if (parts.length != 2) {
      throw new IllegalArgumentException("Invalid duration: " + text);
    }
    try {
      int minutes = Integer.parseInt(parts[0].strip());
      int seconds = Integer.parseInt(parts[1].strip());
      if (minutes < 0 || seconds < 0 || seconds > 59) {
        throw new IllegalArgumentException("Invalid duration: " + text);
      }
      return minutes * 60 + seconds;
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid duration: " + text, e);
    }
  }

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
      throw new PlaylistException("Cannot load playlist " + file, e);
    }
  }
}

package com.howtodoinjava.junit.beforeeach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The quick example: @BeforeEach creates a fresh Playlist before every test, so the tests do not affect each other.
 */
class PlaylistTest {

  Playlist playlist;

  @BeforeEach
  void createPlaylist() {
    playlist = new Playlist("road trip");
    playlist.add("Intro");
    System.out.println("@BeforeEach: new playlist, size " + playlist.size());
  }

  @Test
  void addSong() {
    playlist.add("Outro");
    System.out.println("@Test addSong: size " + playlist.size());
    assertEquals(2, playlist.size());
  }

  @Test
  void removeSong() {
    playlist.remove("Intro");
    System.out.println("@Test removeSong: size " + playlist.size());
    assertEquals(0, playlist.size());
  }
}

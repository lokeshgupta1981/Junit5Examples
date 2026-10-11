package com.howtodoinjava.junit.beforeeach;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/**
 * A base class with shared setup. Its @BeforeEach runs before the @BeforeEach methods of subclasses.
 */
abstract class BasePlaylistTest {

  protected Playlist playlist;

  @BeforeEach
  void createPlaylist() {
    playlist = new Playlist("base");
    System.out.println("1. BasePlaylistTest.createPlaylist()");
  }

  @AfterEach
  void printSongs() {
    System.out.println("5. BasePlaylistTest.printSongs(): " + playlist.songs());
  }
}

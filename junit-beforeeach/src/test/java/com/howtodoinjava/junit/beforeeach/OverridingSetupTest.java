package com.howtodoinjava.junit.beforeeach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Overriding the superclass @BeforeEach method replaces it: only the subclass version runs.
 */
class OverridingSetupTest extends BasePlaylistTest {

  @Override
  @BeforeEach
  void createPlaylist() {
    playlist = new Playlist("override");
    System.out.println("1. OverridingSetupTest.createPlaylist()");
  }

  @Test
  void usesSubclassSetup() {
    assertEquals("override", playlist.name());
  }
}

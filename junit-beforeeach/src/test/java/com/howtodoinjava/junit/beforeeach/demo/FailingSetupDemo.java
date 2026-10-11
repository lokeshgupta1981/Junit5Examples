package com.howtodoinjava.junit.beforeeach.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.howtodoinjava.junit.beforeeach.Playlist;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Fails on purpose: an exception in @BeforeEach fails the test, the test method never runs, @AfterEach still runs.
 * Run with: mvn test -Dtest=FailingSetupDemo
 */
class FailingSetupDemo {

  Playlist playlist;

  @BeforeEach
  void loadPlaylist() {
    System.out.println("@BeforeEach loadPlaylist()");
    throw new IllegalStateException("Playlist file not found: road-trip.txt");
  }

  @Test
  void addSong() {
    System.out.println("@Test addSong");      // never printed
    playlist.add("Outro");
    assertEquals(1, playlist.size());
  }

  @AfterEach
  void cleanUp() {
    System.out.println("@AfterEach cleanUp(), playlist = " + playlist);
  }
}

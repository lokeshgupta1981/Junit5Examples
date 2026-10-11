package com.howtodoinjava.junit.beforeeach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Prints the order of all four lifecycle methods and shows that JUnit creates a new test instance for every test
 * (the default PER_METHOD lifecycle).
 */
class LifecycleOrderTest {

  static int instances = 0;

  Playlist playlist;

  LifecycleOrderTest() {
    instances++;
    System.out.println("constructor: test instance #" + instances);
  }

  @BeforeAll
  static void beforeAll() {
    System.out.println("@BeforeAll");
  }

  @BeforeEach
  void beforeEach() {
    playlist = new Playlist("focus");
    System.out.println("  @BeforeEach");
  }

  @Test
  void addSong() {
    playlist.add("Rain");
    System.out.println("    @Test addSong");
    assertEquals(1, playlist.size());
  }

  @Test
  void emptyPlaylist() {
    System.out.println("    @Test emptyPlaylist");
    assertEquals(0, playlist.size());
  }

  @AfterEach
  void afterEach() {
    System.out.println("  @AfterEach");
  }

  @AfterAll
  static void afterAll() {
    System.out.println("@AfterAll");
  }
}

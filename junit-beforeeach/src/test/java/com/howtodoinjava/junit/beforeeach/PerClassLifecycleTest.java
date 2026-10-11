package com.howtodoinjava.junit.beforeeach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * With PER_CLASS, JUnit creates one test instance for all tests, so @BeforeAll and @AfterAll can be non-static.
 * Fields keep their values between tests, so @BeforeEach resets the playlist.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PerClassLifecycleTest {

  static int instances = 0;

  Playlist playlist;
  int testsRun = 0;

  PerClassLifecycleTest() {
    instances++;
    System.out.println("constructor: test instance #" + instances);
  }

  @BeforeAll
  void beforeAll() {                       // non-static is allowed with PER_CLASS
    System.out.println("@BeforeAll (non-static)");
  }

  @BeforeEach
  void beforeEach() {
    playlist = new Playlist("focus");      // reset, the instance is shared
    testsRun++;
    System.out.println("  @BeforeEach, testsRun = " + testsRun);
  }

  @Test
  void addSong() {
    playlist.add("Rain");
    assertEquals(1, playlist.size());
  }

  @Test
  void emptyPlaylist() {
    assertEquals(0, playlist.size());
  }

  @AfterAll
  void afterAll() {
    System.out.println("@AfterAll (non-static), testsRun = " + testsRun);
    assertEquals(1, instances);
    assertEquals(2, testsRun);
  }
}

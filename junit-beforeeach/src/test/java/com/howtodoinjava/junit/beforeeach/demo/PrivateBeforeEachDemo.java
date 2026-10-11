package com.howtodoinjava.junit.beforeeach.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A private @BeforeEach method is discouraged by the JUnit Javadoc.
 * Run with: mvn test -Dtest=PrivateBeforeEachDemo
 */
class PrivateBeforeEachDemo {

  @BeforeEach
  private void createPlaylist() {
    System.out.println("@BeforeEach createPlaylist()");
  }

  @Test
  void addSong() {
    System.out.println("@Test addSong");
  }
}

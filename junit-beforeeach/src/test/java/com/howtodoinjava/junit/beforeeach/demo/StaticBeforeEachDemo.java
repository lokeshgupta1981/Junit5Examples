package com.howtodoinjava.junit.beforeeach.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Invalid on purpose: @BeforeEach must not be static.
 * Run with: mvn test -Dtest=StaticBeforeEachDemo
 */
class StaticBeforeEachDemo {

  @BeforeEach
  static void createPlaylist() {
    System.out.println("@BeforeEach createPlaylist()");
  }

  @Test
  void addSong() {
    System.out.println("@Test addSong");
  }
}

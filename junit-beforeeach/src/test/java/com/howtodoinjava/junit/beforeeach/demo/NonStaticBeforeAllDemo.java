package com.howtodoinjava.junit.beforeeach.demo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Invalid on purpose: without @TestInstance(PER_CLASS), @BeforeAll must be static.
 * Run with: mvn test -Dtest=NonStaticBeforeAllDemo
 */
class NonStaticBeforeAllDemo {

  @BeforeAll
  void connect() {
    System.out.println("@BeforeAll connect()");
  }

  @Test
  void addSong() {
    System.out.println("@Test addSong");
  }
}

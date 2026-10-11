package com.howtodoinjava.junit.perclass.demo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * No @TestInstance here: the per-class default comes from junit-platform.properties.
 * Run with: mvn test -Pper-class -Dtest=DefaultPerClassDemo
 */
class DefaultPerClassDemo {

  @BeforeAll
  void openLibrary() {
    System.out.println("@BeforeAll openLibrary() without @TestInstance");
  }

  @Test
  void borrowsBook() {
    System.out.println("@Test borrowsBook");
  }

  @AfterAll
  void closeLibrary() {
    System.out.println("@AfterAll closeLibrary() without @TestInstance");
  }
}

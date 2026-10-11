package com.howtodoinjava.junit.perclass;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * With PER_CLASS, fields keep their values between tests, so @BeforeEach resets the mutable ones.
 * A @Nested class can also use PER_CLASS for its own non-static @BeforeAll.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BorrowingTest {

  List<String> borrowed;

  @BeforeAll
  void openLibrary() {
    System.out.println("@BeforeAll openLibrary()");
  }

  @BeforeAll
  static void checkRules() {                    // static is also allowed with PER_CLASS
    System.out.println("@BeforeAll checkRules() (static)");
  }

  @BeforeEach
  void resetBorrowed() {
    borrowed = new ArrayList<>();                 // fresh list, the instance is shared
  }

  @Test
  void borrowsOneBook() {
    borrowed.add("Emma");
    assertEquals(1, borrowed.size());
  }

  @Test
  void borrowsTwoBooks() {
    borrowed.add("Emma");
    borrowed.add("Dune");
    assertEquals(2, borrowed.size());
  }

  @Nested
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  class Reservations {

    List<String> queue;

    @BeforeAll
    void loadQueue() {                            // non-static in a @Nested class
      queue = List.of("alex", "maria");
      System.out.println("  @BeforeAll Reservations.loadQueue()");
    }

    @Test
    void firstInQueue() {
      assertEquals("alex", queue.getFirst());
    }
  }
}

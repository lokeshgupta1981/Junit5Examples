package com.howtodoinjava.junit6;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

// JUnit 5 code used MethodOrderer.Alphanumeric, which JUnit 6 removed
@TestMethodOrder(MethodOrderer.MethodName.class)
class MethodOrderTest {

  @Test
  void bTest() {
    System.out.println("MethodOrderTest.bTest");
  }

  @Test
  void aTest() {
    System.out.println("MethodOrderTest.aTest");
  }

  // In JUnit 6, @Nested classes inherit @TestMethodOrder from the enclosing class
  @Nested
  class Inner {

    @Test
    void dTest() {
      System.out.println("Inner.dTest");
    }

    @Test
    void cTest() {
      System.out.println("Inner.cTest");
    }
  }
}

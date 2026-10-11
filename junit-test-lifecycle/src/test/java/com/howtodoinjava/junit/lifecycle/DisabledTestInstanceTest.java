package com.howtodoinjava.junit.lifecycle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * JUnit creates the test instance even for a disabled test method, but it runs no @BeforeEach for it.
 */
class DisabledTestInstanceTest {

  DisabledTestInstanceTest() {
    System.out.println("constructor of DisabledTestInstanceTest");
  }

  @BeforeEach
  void beforeEach() {
    System.out.println("@BeforeEach of DisabledTestInstanceTest");
  }

  @Disabled("International rates are not ready")
  @Test
  void internationalCost() {
    System.out.println("@Test internationalCost");
  }
}

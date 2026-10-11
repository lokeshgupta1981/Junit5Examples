package com.howtodoinjava.junit.beforeall.demo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

/**
 * Invalid on purpose: without @TestInstance(PER_CLASS), @AfterAll must be static.
 * Run with: mvn test -Dtest=NonStaticAfterAllDemo
 */
class NonStaticAfterAllDemo {

  @Test
  void convertsUsdToEur() {
    System.out.println("@Test convertsUsdToEur");
  }

  @AfterAll
  void stopServer() {
    System.out.println("@AfterAll stopServer()");
  }
}

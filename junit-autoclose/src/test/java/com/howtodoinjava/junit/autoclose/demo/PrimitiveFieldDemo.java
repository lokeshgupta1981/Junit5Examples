package com.howtodoinjava.junit.autoclose.demo;

import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;

/**
 * Invalid on purpose: a primitive field cannot be closed.
 * Run with: mvn test -Dtest=PrimitiveFieldDemo
 */
class PrimitiveFieldDemo {

  @AutoClose
  int retries = 3;

  @Test
  void placesOrder() {
    System.out.println("@Test placesOrder");
  }
}

package com.howtodoinjava.junit.autoclose.demo;

import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;

/**
 * Invalid on purpose: a String has no close() method.
 * Run with: mvn test -Dtest=InvalidFieldDemo
 */
class InvalidFieldDemo {

  @AutoClose
  String customer = "alex";

  @Test
  void placesOrder() {
    System.out.println("@Test placesOrder");
  }
}

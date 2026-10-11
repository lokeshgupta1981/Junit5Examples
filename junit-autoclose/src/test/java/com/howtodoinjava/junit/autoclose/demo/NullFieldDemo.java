package com.howtodoinjava.junit.autoclose.demo;

import com.howtodoinjava.junit.autoclose.PriceFeed;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;

/**
 * A null @AutoClose field is skipped with a warning.
 * Run with: mvn test -Dtest=NullFieldDemo
 */
class NullFieldDemo {

  @AutoClose("disconnect")
  PriceFeed priceFeed;                                   // never assigned

  @Test
  void worksOffline() {
    System.out.println("@Test worksOffline");
  }
}

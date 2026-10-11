package com.howtodoinjava.junit.assumptions;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/** A failed assumption in @BeforeAll aborts every test in the class. @AfterAll still runs. */
class BeforeAllAssumptionTest {

  @BeforeAll
  static void checkSandbox() {
    assumeTrue(System.getenv("PAYMENT_SANDBOX_URL") != null, "PAYMENT_SANDBOX_URL is not set");
  }

  @Test
  void charge() {
    System.out.println("charge");
  }

  @Test
  void refund() {
    System.out.println("refund");
  }

  @AfterAll
  static void cleanUp() {
    System.out.println("@AfterAll runs");
  }
}

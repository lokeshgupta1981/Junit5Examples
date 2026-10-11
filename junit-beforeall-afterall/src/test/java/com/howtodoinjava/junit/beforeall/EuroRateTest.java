package com.howtodoinjava.junit.beforeall;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * The subclass @BeforeAll can use the server that the superclass @BeforeAll started.
 */
class EuroRateTest extends AbstractRateServerTest {

  static RateClient client;

  @BeforeAll
  static void createClient(TestInfo testInfo) {
    client = new RateClient(baseUri);              // baseUri is set here
    System.out.println("2. " + testInfo.getDisplayName() + ".createClient()");
  }

  @Test
  void convertsEurToUsd() throws Exception {
    System.out.println("3. @Test convertsEurToUsd");
    assertEquals(new BigDecimal("1.09"), client.rate("EUR", "USD"));
  }

  @AfterAll
  static void printSummary() {
    System.out.println("4. EuroRateTest.printSummary()");
  }
}

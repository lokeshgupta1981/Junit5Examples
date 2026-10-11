package com.howtodoinjava.junit.beforeall.demo;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Fails on purpose: an exception in @BeforeAll skips every test of the class, and @AfterAll still runs.
 * Run with: mvn test -Dtest=FailingBeforeAllDemo
 */
class FailingBeforeAllDemo {

  static HttpServer server;

  @BeforeAll
  static void startServer() {
    System.out.println("@BeforeAll startServer()");
    throw new IllegalStateException("Port 8089 is already in use");
  }

  @Test
  void convertsUsdToEur() {
    System.out.println("@Test convertsUsdToEur");    // never printed
  }

  @Test
  void convertsUsdToInr() {
    System.out.println("@Test convertsUsdToInr");    // never printed
  }

  @AfterAll
  static void stopServer() {
    if (server != null) {
      server.stop(0);
    }
    System.out.println("@AfterAll stopServer(), server = " + server);
  }
}

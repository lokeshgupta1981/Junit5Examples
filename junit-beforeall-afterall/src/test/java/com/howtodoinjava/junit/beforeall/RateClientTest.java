package com.howtodoinjava.junit.beforeall;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.URI;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * The quick example: one stub HTTP server for all tests of the class, started in @BeforeAll and stopped in @AfterAll.
 */
class RateClientTest {

  static HttpServer server;
  static RateClient client;

  @BeforeAll
  static void startServer() throws IOException {
    server = HttpServer.create(new InetSocketAddress("localhost", 0), 0);
    server.createContext("/rates/USD/EUR", exchange -> RateStub.respond(exchange, "0.92"));
    server.createContext("/rates/USD/INR", exchange -> RateStub.respond(exchange, "88.50"));
    server.start();
    client = new RateClient(URI.create("http://localhost:" + server.getAddress().getPort()));
    System.out.println("@BeforeAll: rate server started");
  }

  @AfterAll
  static void stopServer() {
    server.stop(0);
    System.out.println("@AfterAll: rate server stopped");
  }

  @Test
  void convertsUsdToEur() throws Exception {
    System.out.println("  @Test convertsUsdToEur");
    assertEquals(new BigDecimal("0.92"), client.rate("USD", "EUR"));
  }

  @Test
  void convertsUsdToInr() throws Exception {
    System.out.println("  @Test convertsUsdToInr");
    assertEquals(new BigDecimal("88.50"), client.rate("USD", "INR"));
  }

  @Test
  void rejectsUnknownPair() {
    System.out.println("  @Test rejectsUnknownPair");
    assertThrows(IllegalStateException.class, () -> client.rate("USD", "XYZ"));
  }
}

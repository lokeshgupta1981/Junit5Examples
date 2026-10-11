package com.howtodoinjava.junit.beforeall;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

/**
 * A base class that starts the stub server once per test class. Its @BeforeAll runs before the subclass @BeforeAll,
 * and its @AfterAll runs after the subclass @AfterAll.
 */
abstract class AbstractRateServerTest {

  static HttpServer server;
  static URI baseUri;

  @BeforeAll
  static void startServer() throws IOException {
    server = HttpServer.create(new InetSocketAddress("localhost", 0), 0);
    server.createContext("/rates/EUR/USD", exchange -> RateStub.respond(exchange, "1.09"));
    server.start();
    baseUri = URI.create("http://localhost:" + server.getAddress().getPort());
    System.out.println("1. AbstractRateServerTest.startServer()");
  }

  @AfterAll
  static void stopServer() {
    server.stop(0);
    System.out.println("5. AbstractRateServerTest.stopServer()");
  }
}

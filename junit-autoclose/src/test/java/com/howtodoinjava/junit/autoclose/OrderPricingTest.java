package com.howtodoinjava.junit.autoclose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.net.http.HttpClient;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;

/**
 * The quick example: three kinds of @AutoClose fields.
 */
class OrderPricingTest {

  @AutoClose
  HttpClient httpClient = HttpClient.newHttpClient();              // close() after each test

  @AutoClose
  static ExecutorService pool = Executors.newFixedThreadPool(2);  // close() after all tests

  @AutoClose("disconnect")
  PriceFeed priceFeed = new PriceFeed();                          // disconnect() after each test

  @Test
  void pricesItemsInParallel() throws Exception {
    Future<BigDecimal> apple = pool.submit(() -> priceFeed.price("apple"));
    Future<BigDecimal> banana = pool.submit(() -> priceFeed.price("banana"));
    assertEquals(new BigDecimal("1.70"), apple.get().add(banana.get()));
  }

  @Test
  void createsHttpClientPerTest() {
    assertEquals(HttpClient.Version.HTTP_2, httpClient.version());
  }
}

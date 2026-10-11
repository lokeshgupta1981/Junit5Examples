package com.howtodoinjava.junit.autoclose;

import java.math.BigDecimal;
import java.util.Map;

/**
 * A client for a market price service. It has no close() method; we end the session with disconnect().
 */
public class PriceFeed {

  private final Map<String, BigDecimal> prices = Map.of("apple", new BigDecimal("1.20"), "banana", new BigDecimal("0.50"));
  private boolean connected = true;

  public BigDecimal price(String item) {
    if (!connected) {
      throw new IllegalStateException("PriceFeed is disconnected");
    }
    return prices.get(item);
  }

  public void disconnect() {
    connected = false;
    System.out.println("PriceFeed.disconnect()");
  }

  public boolean isConnected() {
    return connected;
  }
}

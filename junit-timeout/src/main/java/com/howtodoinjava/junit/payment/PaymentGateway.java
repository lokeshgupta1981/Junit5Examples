package com.howtodoinjava.junit.payment;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class PaymentGateway {

  private final Duration latency;

  public PaymentGateway(Duration latency) {
    this.latency = latency;
  }

  public String charge(int amountInCents) throws InterruptedException {
    if (amountInCents <= 0) {
      throw new IllegalArgumentException("amount must be positive");
    }
    Latency.waitFor(latency);
    return "APPROVED";
  }

  public CompletableFuture<String> chargeAsync(int amountInCents) {
    return CompletableFuture.supplyAsync(() -> {
      try {
        return charge(amountInCents);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        return "INTERRUPTED";
      }
    });
  }

  public long fraudScore(Duration work) {
    return Latency.spin(work) % 100;
  }
}

package com.howtodoinjava.junit.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PaymentTimeoutTest {

  private PaymentGateway gateway;

  @BeforeEach
  @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
  void connect() {
    gateway = new PaymentGateway(Duration.ofMillis(50));
  }

  @Test
  @Timeout(2)
  void chargeIsApprovedInTime() throws InterruptedException {
    String status = gateway.charge(4999);         // takes about 50 ms
    assertEquals("APPROVED", status);
  }

  @Test
  @Timeout(value = 300, unit = TimeUnit.MILLISECONDS)
  void chargeWithinThreeHundredMillis() throws InterruptedException {
    assertEquals("APPROVED", gateway.charge(1500));
  }

  @ParameterizedTest
  @ValueSource(ints = {100, 2500, 99999})
  @Timeout(1)
  void everyAmountIsChargedWithinOneSecond(int amountInCents) throws InterruptedException {
    assertEquals("APPROVED", gateway.charge(amountInCents));
  }

  @Test
  @Timeout(5)
  void asyncChargeIsConfirmed() {
    CompletableFuture<String> confirmation = gateway.chargeAsync(4999);
    while (!confirmation.isDone()) {
      Thread.onSpinWait();
    }
    assertEquals("APPROVED", confirmation.join());
  }

  @Test
  void usesTheDefaultFromProperties() throws InterruptedException {
    assertEquals("APPROVED", gateway.charge(700));   // limited by test.method.default=5 s
  }
}

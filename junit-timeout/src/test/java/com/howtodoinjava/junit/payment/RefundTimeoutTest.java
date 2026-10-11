package com.howtodoinjava.junit.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(3)
class RefundTimeoutTest {

  private final PaymentGateway gateway = new PaymentGateway(Duration.ofMillis(100));

  @Test
  void refundUsesClassTimeout() throws InterruptedException {
    assertEquals("APPROVED", gateway.charge(1000));   // limit 3 seconds from the class
  }

  @Test
  @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
  void partialRefundHasItsOwnTimeout() throws InterruptedException {
    assertEquals("APPROVED", gateway.charge(250));    // limit 200 ms from the method
  }

  @Nested
  class BulkRefunds {

    @Test
    void inheritsClassTimeout() throws InterruptedException {
      assertEquals("APPROVED", gateway.charge(5000)); // limit 3 seconds from the outer class
    }
  }
}

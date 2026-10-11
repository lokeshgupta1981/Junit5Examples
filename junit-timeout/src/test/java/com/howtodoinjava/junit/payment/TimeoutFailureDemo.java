package com.howtodoinjava.junit.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

// Fails on purpose to show the timeout failures. Not matched by the default Surefire includes;
// run it with: mvn test -Dtest=TimeoutFailureDemo
class TimeoutFailureDemo {

  private final PaymentGateway slowGateway = new PaymentGateway(Duration.ofSeconds(3));

  @Test
  @Timeout(1)
  void slowGatewayTimesOut() throws InterruptedException {
    assertEquals("APPROVED", slowGateway.charge(4999));
  }

  @Test
  @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
  void busyLoopIgnoresInterrupt() {
    long score = slowGateway.fraudScore(Duration.ofMillis(1500));   // never checks the interrupt flag
    assertTrue(score >= 0);
  }

  @Test
  @Timeout(value = 500, unit = TimeUnit.MILLISECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
  void busyLoopInSeparateThread() {
    long score = slowGateway.fraudScore(Duration.ofMillis(1500));
    assertTrue(score >= 0);
  }

  @Test
  void slowChargeHitsTheDefault() throws InterruptedException {
    PaymentGateway verySlowGateway = new PaymentGateway(Duration.ofSeconds(8));
    assertEquals("APPROVED", verySlowGateway.charge(4999));         // no @Timeout, default is 5 s
  }
}

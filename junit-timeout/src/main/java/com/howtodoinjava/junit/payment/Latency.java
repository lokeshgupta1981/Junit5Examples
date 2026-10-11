package com.howtodoinjava.junit.payment;

import java.time.Duration;
import java.util.concurrent.locks.LockSupport;

/** Simulates network latency. Reacts to Thread.interrupt() like a blocking I/O call would. */
public final class Latency {

  private Latency() {
  }

  public static void waitFor(Duration duration) throws InterruptedException {
    long end = System.nanoTime() + duration.toNanos();
    while (System.nanoTime() < end) {
      LockSupport.parkNanos(Math.min(end - System.nanoTime(), Duration.ofMillis(10).toNanos()));
      if (Thread.interrupted()) {
        throw new InterruptedException("interrupted while waiting for the gateway");
      }
    }
  }

  /** Busy work that never checks the interrupt flag, like a tight CPU loop. */
  public static long spin(Duration duration) {
    long end = System.nanoTime() + duration.toNanos();
    long rounds = 0;
    while (System.nanoTime() < end) {
      rounds++;
    }
    return rounds;
  }
}

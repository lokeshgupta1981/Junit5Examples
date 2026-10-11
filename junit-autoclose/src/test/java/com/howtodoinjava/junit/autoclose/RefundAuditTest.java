package com.howtodoinjava.junit.autoclose;

import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;

class RefundAuditTest extends AbstractAuditTest {

  @AutoClose
  AuditLog refundLog = new AuditLog("subclass field");

  @Test
  void refundsOrder() {
    System.out.println("@Test refundsOrder");
  }
}

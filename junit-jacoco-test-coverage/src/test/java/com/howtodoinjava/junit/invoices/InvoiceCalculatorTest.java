package com.howtodoinjava.junit.invoices;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class InvoiceCalculatorTest {

  private final InvoiceCalculator calculator = new InvoiceCalculator();

  @Test
  void firstOrderGetsTenPercent() {
    assertEquals(new BigDecimal("90.00"), calculator.total(new BigDecimal("100.00"), true));
  }

  @Test
  void overdueInvoice() {
    assertEquals("OVERDUE", calculator.dueLabel(-3));
  }
}

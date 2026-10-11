package com.howtodoinjava.junit.invoices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;

class InvoiceEdgeCasesTest {

  private final InvoiceCalculator calculator = new InvoiceCalculator();

  @ParameterizedTest
  @CsvSource({"640.00, 608.00", "499.99, 499.99"})
  void bulkDiscountStartsAtFiveHundred(BigDecimal amount, BigDecimal expected) {
    assertEquals(expected, calculator.total(amount, false));
  }

  @Test
  void negativeAmountIsRejected() {
    assertThrows(IllegalArgumentException.class,
        () -> calculator.total(new BigDecimal("-1"), false));
  }

  @Test
  void dueToday() {
    assertEquals("DUE TODAY", calculator.dueLabel(0));
  }
}

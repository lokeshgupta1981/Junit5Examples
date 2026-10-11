package com.howtodoinjava.junit.invoices;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class InvoiceCalculator {

  public BigDecimal discountPercent(BigDecimal amount, boolean firstOrder) {
    if (firstOrder) {
      return new BigDecimal("10");
    }
    if (amount.compareTo(new BigDecimal("500")) >= 0) {
      return new BigDecimal("5");
    }
    return BigDecimal.ZERO;
  }

  public BigDecimal total(BigDecimal amount, boolean firstOrder) {
    if (amount.signum() < 0) {
      throw new IllegalArgumentException("Amount must not be negative");
    }
    BigDecimal discount = amount.multiply(discountPercent(amount, firstOrder))
        .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
    return amount.subtract(discount).setScale(2, RoundingMode.HALF_UP);
  }

  public String dueLabel(int daysUntilDue) {
    if (daysUntilDue < 0) {
      return "OVERDUE";
    } else if (daysUntilDue == 0) {
      return "DUE TODAY";
    }
    return "DUE IN " + daysUntilDue + " DAYS";
  }
}

package com.howtodoinjava.junit.aftereach;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Formats invoice amounts with the default locale of the JVM.
 */
public final class InvoiceFormatter {

  private InvoiceFormatter() {
  }

  public static String format(BigDecimal amount) {
    NumberFormat format = NumberFormat.getNumberInstance(Locale.getDefault());
    format.setMinimumFractionDigits(2);
    format.setMaximumFractionDigits(2);
    return format.format(amount);
  }
}

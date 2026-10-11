package com.howtodoinjava.junit.aftereach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Locale;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests that change JVM-wide settings, such as the default locale, restore them in @AfterEach.
 */
class LocaleResetTest {

  Locale originalLocale;

  @BeforeEach
  void saveLocale() {
    originalLocale = Locale.getDefault();
  }

  @AfterEach
  void restoreLocale() {
    Locale.setDefault(originalLocale);
  }

  @Test
  void formatsGermanAmount() {
    Locale.setDefault(Locale.GERMANY);
    String text = InvoiceFormatter.format(new BigDecimal("1234.5"));
    assertEquals("1.234,50", text);
  }

  @Test
  void formatsUsAmount() {
    Locale.setDefault(Locale.US);
    String text = InvoiceFormatter.format(new BigDecimal("1234.5"));
    assertEquals("1,234.50", text);
  }
}

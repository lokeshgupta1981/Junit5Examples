package com.howtodoinjava.junit6;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.TimeZone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.util.DefaultLocale;
import org.junit.jupiter.api.util.DefaultTimeZone;
import org.junit.jupiter.api.util.SetSystemProperty;

// JUnit 6.1 ships these extensions; JUnit 5 projects needed JUnit Pioneer for them
class BuiltInExtensionsTest {

  private final PriceCalculator calculator = new PriceCalculator();

  @Test
  @DefaultLocale("en-US")
  void formatsUsDollars() {
    assertEquals("$10.00", calculator.format(10.0));
  }

  @Test
  @DefaultTimeZone("Asia/Kolkata")
  void usesKolkataTimeZone() {
    assertEquals("Asia/Kolkata", TimeZone.getDefault().getID());
  }

  @Test
  @SetSystemProperty(key = "store.currency", value = "EUR")
  void readsSystemProperty() {
    assertEquals("EUR", System.getProperty("store.currency"));
  }
}

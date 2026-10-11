package com.howtodoinjava.junit.payments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardValidatorTest {

  private final CardValidator validator = new CardValidator();

  @Test
  @DisplayName("Valid test card number passes the Luhn check")
  void validNumber() {
    assertTrue(validator.passesLuhnCheck("4111 1111 1111 1111"));
  }

  @Test
  @DisplayName("Mistyped digit fails the Luhn check")
  void mistypedNumber() {
    assertFalse(validator.passesLuhnCheck("4111 1111 1111 1112"));
  }

  @Test
  void maskKeepsLastFourDigits() {
    String masked = validator.mask("4111 1111 1111 1111");
    System.out.println("Masked card: " + masked);
    assertEquals("**** 1111", masked);
  }

  @Test
  @Disabled("Waiting for the new card network rules")
  void rejectsExpiredNetworkPrefix() {
  }
}

package com.howtodoinjava.junit.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.howtodoinjava.junit.payments.CardValidator;
import org.junit.jupiter.api.Test;

// Fails on purpose to show a <failure> element in the XML reports. Excluded from the default build.
class CardMaskingDemoTest {

  @Test
  void maskShowsLastFourDigits() {
    assertEquals("**** 1112", new CardValidator().mask("4111 1111 1111 1111"));
  }
}

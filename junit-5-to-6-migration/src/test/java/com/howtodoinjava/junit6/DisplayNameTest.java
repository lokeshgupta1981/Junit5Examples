package com.howtodoinjava.junit6;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DisplayNameTest {

  private final PriceCalculator calculator = new PriceCalculator();

  @ParameterizedTest
  @CsvSource({
      "apple,  5, 2.0, 10.0",
      "banana, 3, 1.5, 4.5"
  })
  void total(String fruit, int quantity, double unitPrice, double expected, TestInfo info) {
    System.out.println("Display name: " + info.getDisplayName());
    assertEquals(expected, calculator.total(quantity, unitPrice));
  }
}

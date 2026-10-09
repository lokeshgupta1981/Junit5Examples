package com.howtodoinjava.junit6;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class CsvFileSourceTest {

  private final PriceCalculator calculator = new PriceCalculator();

  // JUnit 5 code had lineSeparator = "\n" here; JUnit 6 removed the attribute
  // and detects \n, \r and \r\n by itself
  @ParameterizedTest
  @CsvFileSource(resources = "/prices.csv", numLinesToSkip = 1)
  void totalFromFile(String fruit, int quantity, double unitPrice, double expected) {
    assertEquals(expected, calculator.total(quantity, unitPrice));
  }
}

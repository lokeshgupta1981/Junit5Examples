package com.howtodoinjava.junit6;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

// Runs on JUnit 5, fails on JUnit 6: text after a closing quote is rejected.
// Excluded by default; run it with: mvn test -Dgroups=fails-on-junit6 -DexcludedTags=none
@Tag("fails-on-junit6")
class CsvStrictQuotesTest {

  @ParameterizedTest
  @CsvSource("'apple'x, 5")
  void textAfterClosingQuote(String fruit, int quantity) {
    assertNotNull(fruit);
  }
}

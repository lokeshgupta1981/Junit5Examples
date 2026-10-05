package com.howtodoinjava.assertj;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StringAssertionsTest {

  @Test
  void strings() {
    String name = "Pancakes";

    assertThat(name).isEqualTo("Pancakes");                    // passes
    assertThat(name).isEqualToIgnoringCase("PANCAKES");        // passes
    assertThat(name).startsWith("Pan").endsWith("cakes");      // passes
    assertThat(name).contains("cake").doesNotContain("waffle");// passes
    assertThat(name).containsIgnoringCase("CAKE");             // passes
    assertThat(name).hasSize(8).isNotBlank();                  // passes
    assertThat(name).matches("[A-Z][a-z]+");                   // passes
    assertThat("  ").isBlank();                                // passes
    assertThat("15").containsOnlyDigits();                     // passes
    assertThat("Pan  cakes").isEqualToIgnoringWhitespace("Pancakes"); // passes
    assertThat("Pan  cakes").isEqualToNormalizingWhitespace("Pan cakes"); // passes

    String missing = null;
    assertThat(missing).isNullOrEmpty();                       // passes
  }
}

package com.howtodoinjava.assertj;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.within;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class IntroTest {

  @Test
  void quickReference() {
    List<String> fruits = List.of("apple", "banana", "cherry");
    Map<String, Integer> ages = Map.of("Lokesh", 37, "Alex", 29);

    assertThat("apple").startsWith("app").hasSize(5);                   // passes
    assertThat(0.1 + 0.2).isCloseTo(0.3, within(0.0001));             // passes
    assertThat(fruits).hasSize(3).containsExactly("apple", "banana", "cherry"); // passes
    assertThat(ages).containsEntry("Lokesh", 37).doesNotContainKey("John");     // passes
    assertThatThrownBy(() -> Integer.parseInt("abc"))
        .isInstanceOf(NumberFormatException.class)
        .hasMessageContaining("abc");                                  // passes

    Throwable failure = catchThrowable(() -> assertThat(fruits).contains("kiwi"));
    System.out.println(failure.getMessage());
    assertThat(failure).isInstanceOf(AssertionError.class)
        .hasMessageContaining("could not find the following element(s)");
  }
}

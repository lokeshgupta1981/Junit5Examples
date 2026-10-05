package com.howtodoinjava.assertj;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

class FailureMessagesTest {

  List<String> fruits = List.of("apple", "cherry", "banana");

  @Test
  void containsCheck() {
    Throwable junit = catchThrowable(() -> assertTrue(fruits.contains("kiwi")));
    Throwable assertj = catchThrowable(() -> assertThat(fruits).contains("kiwi"));
    print("assertTrue", junit);
    print("contains", assertj);
    assertThat(junit).hasMessage("expected: <true> but was: <false>");
    assertThat(assertj).hasMessageContaining("[\"kiwi\"]");
  }

  @Test
  void orderCheck() {
    Throwable junit = catchThrowable(
        () -> assertEquals(List.of("apple", "banana", "cherry"), fruits));
    Throwable assertj = catchThrowable(
        () -> assertThat(fruits).containsExactly("apple", "banana", "cherry"));
    print("assertEquals", junit);
    print("containsExactly", assertj);
    assertThat(assertj).hasMessageContaining("element at index 1: expected \"banana\" but was \"cherry\"");
  }

  @Test
  void description() {
    int minutes = 90;
    Throwable junit = catchThrowable(() -> assertTrue(minutes <= 30, "Lasagna is too slow"));
    Throwable assertj = catchThrowable(
        () -> assertThat(minutes).as("Lasagna cooking time").isLessThanOrEqualTo(30));
    print("assertTrue with message", junit);
    print("as()", assertj);
    assertThat(assertj).hasMessageStartingWith("[Lasagna cooking time]");
  }

  private static void print(String label, Throwable t) {
    System.out.println("----- " + label + " -----");
    System.out.println(t.getMessage());
  }
}

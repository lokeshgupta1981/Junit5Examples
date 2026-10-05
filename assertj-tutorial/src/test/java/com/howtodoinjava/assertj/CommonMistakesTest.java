package com.howtodoinjava.assertj;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.List;
import org.junit.jupiter.api.Test;

class CommonMistakesTest {

  List<String> fruits = List.of("apple", "banana", "cherry");

  @Test
  void forgottenAssertion() {
    assertThat(fruits.contains("kiwi"));                               // passes, checks nothing
    Throwable failure = catchThrowable(() -> assertThat(fruits.contains("kiwi")).isTrue());
    assertThat(failure).isInstanceOf(AssertionError.class);
  }

  @Test
  void sizeVersusHasSize() {
    Throwable bySize = catchThrowable(() -> assertThat(fruits.size()).isEqualTo(4));
    Throwable hasSize = catchThrowable(() -> assertThat(fruits).hasSize(4));
    System.out.println("----- size() -----");
    System.out.println(bySize.getMessage());
    System.out.println("----- hasSize() -----");
    System.out.println(hasSize.getMessage());
    assertThat(hasSize).hasMessageContaining("Expected size: 4 but was: 3");
  }

  @Test
  void descriptionAfterAssertion() {
    Throwable late = catchThrowable(() -> assertThat(90).isLessThan(30).as("Lasagna cooking time"));
    Throwable early = catchThrowable(() -> assertThat(90).as("Lasagna cooking time").isLessThan(30));
    System.out.println("----- as() after -----");
    System.out.println(late.getMessage());
    assertThat(late.getMessage()).doesNotContain("Lasagna cooking time");
    assertThat(early.getMessage()).contains("[Lasagna cooking time]");
  }
}

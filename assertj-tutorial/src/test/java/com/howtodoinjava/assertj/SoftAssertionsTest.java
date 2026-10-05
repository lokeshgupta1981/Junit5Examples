package com.howtodoinjava.assertj;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class SoftAssertionsTest {

  Recipe lasagna = Recipes.LASAGNA;

  @Test
  void assertSoftly() {
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(lasagna.name()).isEqualTo("Lasagna");
      softly.assertThat(lasagna.minutes()).isGreaterThan(60);
      softly.assertThat(lasagna.tags()).contains("dinner");
    });                                                                // passes
  }

  @Test
  void allFailuresReported() {
    Throwable failure = catchThrowable(() -> SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(lasagna.name()).as("name").isEqualTo("Lasagne");
      softly.assertThat(lasagna.minutes()).as("minutes").isLessThan(30);
      softly.assertThat(lasagna.tags()).as("tags").contains("dinner");
    }));
    System.out.println(failure.getMessage());
    assertThat(failure).hasMessageContaining("Multiple Failures (2 failures)");
  }

  @Test
  void explicitAssertAll() {
    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(lasagna.name()).startsWith("Las");
    softly.assertThat(lasagna.added()).hasYear(2026);
    softly.assertAll();                                                // passes
  }
}

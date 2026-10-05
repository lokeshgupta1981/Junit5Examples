package com.howtodoinjava.assertj;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;

class RecursiveComparisonTest {

  // A class without equals(), as returned by many mappers and JPA entities
  static class RecipeSummary {
    final String name;
    final int minutes;

    RecipeSummary(String name, int minutes) {
      this.name = name;
      this.minutes = minutes;
    }
  }

  Recipe saved = new Recipe("Pancakes", 15, List.of("breakfast", "sweet"), LocalDate.of(2026, 10, 5));
  Recipe expected = new Recipe("Pancakes", 15, List.of("breakfast", "sweet"), LocalDate.of(2026, 9, 12));

  @Test
  void recordEquality() {
    Throwable failure = catchThrowable(() -> assertThat(saved).isEqualTo(expected));
    System.out.println(failure.getMessage());
    assertThat(failure).isInstanceOf(AssertionError.class);

    assertThat(saved)
        .usingRecursiveComparison()
        .ignoringFields("added")
        .isEqualTo(expected);                                          // passes
  }

  @Test
  void classWithoutEquals() {
    RecipeSummary actual = new RecipeSummary("Pancakes", 15);
    RecipeSummary other = new RecipeSummary("Pancakes", 15);

    assertThat(actual).isNotEqualTo(other);                            // passes, no equals()
    assertThat(actual).usingRecursiveComparison().isEqualTo(other);    // passes, field by field
  }

  @Test
  void differentTypes() {
    RecipeSummary summary = new RecipeSummary("Pancakes", 15);

    assertThat(saved)
        .usingRecursiveComparison()
        .comparingOnlyFields("name", "minutes")
        .isEqualTo(summary);                                           // passes

    Throwable failure = catchThrowable(() -> assertThat(saved)
        .usingRecursiveComparison()
        .ignoringFields("added")
        .isEqualTo(new Recipe("Pancakes", 20, List.of("sweet"), LocalDate.now())));
    System.out.println(failure.getMessage());
    assertThat(failure).hasMessageContaining("field/property 'minutes' differ");
  }

  @Test
  void fieldByFieldInCollections() {
    List<Recipe> fromDb = List.of(saved);

    assertThat(fromDb)
        .usingRecursiveFieldByFieldElementComparatorIgnoringFields("added")
        .containsExactly(expected);                                    // passes
  }
}

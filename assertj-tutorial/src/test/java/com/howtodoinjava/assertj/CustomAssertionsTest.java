package com.howtodoinjava.assertj;

import static com.howtodoinjava.assertj.Recipes.ALL;
import static com.howtodoinjava.assertj.Recipes.LASAGNA;
import static com.howtodoinjava.assertj.Recipes.PANCAKES;
import static org.assertj.core.api.Assertions.allOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.not;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class CustomAssertionsTest {

  Condition<Recipe> quick = new Condition<>(r -> r.minutes() <= 20, "quick (at most 20 minutes)");
  Condition<Recipe> breakfast = new Condition<>(r -> r.tags().contains("breakfast"), "breakfast");

  @Test
  void customAssertClass() {
    RecipeAssert.assertThat(PANCAKES).isQuick().hasTag("sweet");       // passes

    Throwable failure = catchThrowable(() -> RecipeAssert.assertThat(LASAGNA).isQuick());
    System.out.println(failure.getMessage());
    assertThat(failure).hasMessage("Expected recipe <Lasagna> to take at most 20 minutes but it takes <90>");
  }

  @Test
  void conditions() {
    assertThat(PANCAKES).is(quick);                                    // passes
    assertThat(LASAGNA).isNot(quick);                                  // passes
    assertThat(PANCAKES).has(allOf(quick, breakfast));                 // passes
    assertThat(ALL).areExactly(2, quick);                              // passes
    assertThat(ALL).haveAtLeastOne(not(breakfast));                    // passes
    assertThat(ALL).filteredOn(quick).hasSize(2);                      // passes

    Throwable failure = catchThrowable(() -> assertThat(LASAGNA).is(quick));
    System.out.println(failure.getMessage());
    assertThat(failure).hasMessageContaining("to be quick (at most 20 minutes)");
  }

  @Test
  void inlinePredicate() {
    assertThat(PANCAKES).matches(r -> r.minutes() < 30, "takes less than 30 minutes"); // passes
    assertThat(PANCAKES).satisfies(r -> assertThat(r.tags()).contains("sweet"));        // passes
  }
}

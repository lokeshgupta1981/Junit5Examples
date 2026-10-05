package com.howtodoinjava.assertj;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;

class OptionalAssertionsTest {

  RecipeBook book = new RecipeBook().add(Recipes.PANCAKES).add(Recipes.OMELETTE);

  @Test
  void optionals() {
    Optional<Recipe> found = book.findByName("pancakes");
    Optional<Recipe> notFound = book.findByName("Lasagna");

    assertThat(found).isPresent();                                     // passes
    assertThat(found).hasValue(Recipes.PANCAKES);                      // passes
    assertThat(found).hasValueSatisfying(r -> assertThat(r.minutes()).isEqualTo(15)); // passes
    assertThat(found).get().extracting(Recipe::name).isEqualTo("Pancakes"); // passes
    assertThat(found).map(Recipe::minutes).contains(15);               // passes
    assertThat(notFound).isEmpty();                                    // passes
    assertThat(book.findByName(null)).isNotPresent();                  // passes
  }
}

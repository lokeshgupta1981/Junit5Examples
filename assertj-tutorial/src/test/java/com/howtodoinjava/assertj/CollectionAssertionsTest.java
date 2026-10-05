package com.howtodoinjava.assertj;

import static com.howtodoinjava.assertj.Recipes.ALL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.tuple;

import java.util.List;
import org.junit.jupiter.api.Test;

class CollectionAssertionsTest {

  List<String> fruits = List.of("apple", "banana", "cherry");

  @Test
  void containsVariants() {
    assertThat(fruits).hasSize(3).isNotEmpty();                          // passes
    assertThat(fruits).contains("cherry", "apple");                      // passes, any order, subset
    assertThat(fruits).containsExactly("apple", "banana", "cherry");     // passes, same order, all
    assertThat(fruits).containsExactlyInAnyOrder("cherry", "apple", "banana"); // passes, all
    assertThat(fruits).containsOnly("banana", "apple", "cherry", "apple"); // passes, duplicates ignored
    assertThat(fruits).containsSequence("banana", "cherry");             // passes
    assertThat(fruits).doesNotContain("kiwi").doesNotHaveDuplicates();   // passes
    assertThat(fruits).startsWith("apple").endsWith("cherry");           // passes
    assertThat(fruits).allMatch(f -> f.length() >= 5);                   // passes
    assertThat(fruits).anySatisfy(f -> assertThat(f).startsWith("b"));   // passes

    Throwable missing = catchThrowable(
        () -> assertThat(fruits).containsExactlyInAnyOrder("apple", "banana"));
    System.out.println(missing.getMessage());
    assertThat(missing).hasMessageContaining("but the following elements were unexpected");
  }

  @Test
  void extractingAndFiltering() {
    assertThat(ALL)
        .extracting(Recipe::name)
        .containsExactly("Pancakes", "Lasagna", "Omelette");          // passes

    assertThat(ALL)
        .extracting(Recipe::name, Recipe::minutes)
        .contains(tuple("Pancakes", 15), tuple("Omelette", 10));      // passes

    assertThat(ALL)
        .filteredOn(recipe -> recipe.minutes() <= 20)
        .extracting(Recipe::name)
        .containsExactlyInAnyOrder("Pancakes", "Omelette");           // passes

    assertThat(ALL)
        .filteredOn(recipe -> recipe.tags().contains("breakfast"))
        .hasSize(2)
        .allSatisfy(recipe -> assertThat(recipe.minutes()).isLessThan(30)); // passes

    assertThat(ALL)
        .flatExtracting(Recipe::tags)
        .contains("eggs", "baked")
        .hasSize(6);                                                   // passes

    assertThat(ALL)
        .extracting("name")
        .contains("Lasagna");                                          // passes, by property name
  }
}

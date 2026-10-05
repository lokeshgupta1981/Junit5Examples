package com.howtodoinjava.assertj;

import java.time.LocalDate;
import java.util.List;

final class Recipes {

  static final Recipe PANCAKES =
      new Recipe("Pancakes", 15, List.of("breakfast", "sweet"), LocalDate.of(2026, 9, 12));
  static final Recipe LASAGNA =
      new Recipe("Lasagna", 90, List.of("dinner", "baked"), LocalDate.of(2026, 3, 4));
  static final Recipe OMELETTE =
      new Recipe("Omelette", 10, List.of("breakfast", "eggs"), LocalDate.of(2026, 10, 1));

  static final List<Recipe> ALL = List.of(PANCAKES, LASAGNA, OMELETTE);

  private Recipes() {
  }
}

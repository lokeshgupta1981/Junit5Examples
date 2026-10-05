package com.howtodoinjava.assertj;

import java.time.LocalDate;
import java.util.List;

public record Recipe(String name, int minutes, List<String> tags, LocalDate added) {

  public Recipe {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Recipe name must not be blank");
    }
    if (minutes <= 0) {
      throw new IllegalArgumentException("Cooking time must be positive: " + minutes);
    }
    tags = List.copyOf(tags);
  }
}

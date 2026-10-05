package com.howtodoinjava.assertj;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class RecipeBook {

  private final List<Recipe> recipes = new ArrayList<>();

  public RecipeBook add(Recipe recipe) {
    boolean exists = recipes.stream().anyMatch(r -> r.name().equalsIgnoreCase(recipe.name()));
    if (exists) {
      throw new IllegalStateException("Recipe already exists: " + recipe.name());
    }
    recipes.add(recipe);
    return this;
  }

  public Optional<Recipe> findByName(String name) {
    if (name == null) {
      return Optional.empty();
    }
    return recipes.stream().filter(r -> r.name().equalsIgnoreCase(name.strip())).findFirst();
  }

  public List<Recipe> all() {
    return List.copyOf(recipes);
  }
}

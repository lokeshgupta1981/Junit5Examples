package com.howtodoinjava.recipes;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/** A real repository backed by a HashMap. */
public class InMemoryRecipeRepository implements RecipeRepository {

  private final Map<String, Recipe> recipes = new HashMap<>();

  @Override
  public Optional<Recipe> findByName(String name) {
    return Optional.ofNullable(recipes.get(name));
  }

  @Override
  public void save(Recipe recipe) {
    recipes.put(recipe.name(), recipe);
  }
}

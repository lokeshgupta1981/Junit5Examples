package com.howtodoinjava.recipes;

import java.util.Optional;

/** Reads and stores recipes, for example in a database. Tests replace it with a mock. */
public interface RecipeRepository {

  Optional<Recipe> findByName(String name);

  void save(Recipe recipe);
}

package com.howtodoinjava.recipes.repository;

import com.howtodoinjava.recipes.model.Recipe;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class RecipeRepository {

  private final Map<String, Recipe> recipes = new ConcurrentHashMap<>();

  public RecipeRepository() {
    recipes.put("pancakes", new Recipe("pancakes", 20));
    recipes.put("omelette", new Recipe("omelette", 10));
  }

  public Optional<Recipe> findByName(String name) {
    return Optional.ofNullable(recipes.get(name));
  }

  public Recipe save(Recipe recipe) {
    recipes.put(recipe.name(), recipe);
    return recipe;
  }
}

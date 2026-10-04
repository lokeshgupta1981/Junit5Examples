package com.howtodoinjava.recipes;

/**
 * A final field set in a no-arg constructor. Mockito skips final fields during field
 * injection, so the real InMemoryRecipeRepository stays in place.
 */
public class FinalFieldService {

  private final RecipeRepository recipeRepository = new InMemoryRecipeRepository();

  public int cookingTime(String name) {
    return recipeRepository.findByName(name)
        .map(Recipe::minutes)
        .orElseThrow(() -> new RecipeNotFoundException(name));
  }
}

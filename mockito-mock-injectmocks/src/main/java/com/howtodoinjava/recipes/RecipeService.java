package com.howtodoinjava.recipes;

/** The class under test: business logic on top of a RecipeRepository (constructor injection). */
public class RecipeService {

  private final RecipeRepository recipeRepository;

  public RecipeService(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  public int cookingTime(String name) {
    return recipeRepository.findByName(name)
        .map(Recipe::minutes)
        .orElseThrow(() -> new RecipeNotFoundException(name));
  }

  public void addRecipe(String name, int minutes) {
    if (minutes <= 0) {
      throw new IllegalArgumentException("minutes must be positive");
    }
    recipeRepository.save(new Recipe(name.trim(), minutes));
  }
}

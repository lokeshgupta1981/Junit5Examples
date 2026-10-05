package com.howtodoinjava.recipes;

/** A record as the class under test: Mockito calls the canonical constructor. */
public record RecipePrinter(RecipeRepository recipeRepository, TimeFormatter timeFormatter) {

  public String print(String name) {
    Recipe recipe = recipeRepository.findByName(name)
        .orElseThrow(() -> new RecipeNotFoundException(name));
    return recipe.name() + ": " + timeFormatter.format(recipe.minutes());
  }
}

package com.howtodoinjava.recipes;

/** Two dependencies in the constructor: a repository (mocked) and a formatter (spied). */
public class MenuService {

  private final RecipeRepository recipeRepository;
  private final TimeFormatter timeFormatter;

  public MenuService(RecipeRepository recipeRepository, TimeFormatter timeFormatter) {
    this.recipeRepository = recipeRepository;
    this.timeFormatter = timeFormatter;
  }

  public String menuLine(String name) {
    Recipe recipe = recipeRepository.findByName(name)
        .orElseThrow(() -> new RecipeNotFoundException(name));
    return recipe.name() + " (" + timeFormatter.format(recipe.minutes()) + ")";
  }
}

package com.howtodoinjava.recipes;

/**
 * Repository in the constructor, formatter only as a field. Mockito uses constructor
 * injection and does not set the formatter field afterwards.
 */
public class MixedInjectionService {

  private final RecipeRepository recipeRepository;
  private TimeFormatter timeFormatter;      // no constructor parameter, no setter

  public MixedInjectionService(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  public String menuLine(String name) {
    Recipe recipe = recipeRepository.findByName(name)
        .orElseThrow(() -> new RecipeNotFoundException(name));
    return recipe.name() + " (" + timeFormatter.format(recipe.minutes()) + ")";
  }
}

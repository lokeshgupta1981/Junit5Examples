package com.howtodoinjava.recipes;

/** No constructor and no setter: Mockito uses field injection through reflection. */
public class FieldRecipeService {

  private RecipeRepository recipeRepository;

  public int cookingTime(String name) {
    return recipeRepository.findByName(name).map(Recipe::minutes).orElse(0);
  }
}

package com.howtodoinjava.recipes;

/** No-arg constructor and a setter: Mockito uses setter (property) injection. */
public class SetterRecipeService {

  private RecipeRepository recipeRepository;

  public void setRecipeRepository(RecipeRepository recipeRepository) {
    System.out.println("setRecipeRepository() called with " + recipeRepository);
    this.recipeRepository = recipeRepository;
  }

  public int cookingTime(String name) {
    return recipeRepository.findByName(name).map(Recipe::minutes).orElse(0);
  }
}

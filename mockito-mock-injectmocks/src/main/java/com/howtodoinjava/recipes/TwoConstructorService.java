package com.howtodoinjava.recipes;

/** Two constructors: Mockito picks the one with the most parameters. */
public class TwoConstructorService {

  private final RecipeRepository recipeRepository;
  private final TimeFormatter timeFormatter;

  public TwoConstructorService(RecipeRepository recipeRepository) {
    this(recipeRepository, new TimeFormatter());
    System.out.println("1-arg constructor called");
  }

  public TwoConstructorService(RecipeRepository recipeRepository, TimeFormatter timeFormatter) {
    System.out.println("2-arg constructor called with " + recipeRepository + ", " + timeFormatter);
    this.recipeRepository = recipeRepository;
    this.timeFormatter = timeFormatter;
  }

  public TimeFormatter timeFormatter() {
    return timeFormatter;
  }

  public RecipeRepository recipeRepository() {
    return recipeRepository;
  }
}

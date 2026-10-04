package com.howtodoinjava.recipes;

/** Thrown when a recipe name is unknown. */
public class RecipeNotFoundException extends RuntimeException {

  public RecipeNotFoundException(String name) {
    super("Recipe " + name + " not found");
  }
}

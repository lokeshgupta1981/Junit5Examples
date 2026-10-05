package com.howtodoinjava.recipes.service;

import com.howtodoinjava.recipes.model.Recipe;
import com.howtodoinjava.recipes.repository.RecipeRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

  private final RecipeRepository repository;

  public RecipeService(RecipeRepository repository) {
    this.repository = repository;
  }

  public Optional<Recipe> find(String name) {
    return repository.findByName(name);
  }

  public Recipe add(Recipe recipe) {
    return repository.save(recipe);
  }
}

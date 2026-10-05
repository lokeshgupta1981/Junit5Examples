package com.howtodoinjava.assertj;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

  private final RecipeBook recipeBook;

  public RecipeController(RecipeBook recipeBook) {
    this.recipeBook = recipeBook;
  }

  @GetMapping("/{name}")
  public ResponseEntity<Recipe> byName(@PathVariable String name) {
    return ResponseEntity.of(recipeBook.findByName(name));
  }
}

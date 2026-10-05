package com.howtodoinjava.recipes.controller;

import com.howtodoinjava.recipes.model.Recipe;
import com.howtodoinjava.recipes.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

  private final RecipeService service;

  public RecipeController(RecipeService service) {
    this.service = service;
  }

  @GetMapping("/{name}")
  public ResponseEntity<Recipe> get(@PathVariable String name) {
    return ResponseEntity.of(service.find(name));
  }

  @PostMapping
  public Recipe add(@RequestBody Recipe recipe) {
    return service.add(recipe);
  }
}

package com.howtodoinjava.junit.recipes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeService {

    private final Map<Long, Recipe> recipes = Map.of(
            1L, new Recipe(1, "Pancakes", 20),
            2L, new Recipe(2, "Tomato soup", 35));

    public Optional<Recipe> findById(long id) {
        return Optional.ofNullable(recipes.get(id));
    }

    public List<Recipe> quickRecipes(int maxMinutes) {
        return recipes.values().stream()
                .filter(recipe -> recipe.minutes() <= maxMinutes)
                .toList();
    }
}

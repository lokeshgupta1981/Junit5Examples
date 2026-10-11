package com.howtodoinjava.junit.recipes;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RecipeServiceTest {

    private final RecipeService service = new RecipeService();

    @Test
    void quickRecipesAreUnderTheLimit() {
        assertThat(service.quickRecipes(30))
                .extracting(Recipe::name)
                .containsExactly("Pancakes");
    }
}

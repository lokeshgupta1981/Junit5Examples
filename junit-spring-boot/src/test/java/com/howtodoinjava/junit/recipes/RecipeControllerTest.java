package com.howtodoinjava.junit.recipes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@WebMvcTest(RecipeController.class)
class RecipeControllerTest {

    @Autowired
    private MockMvcTester mvc;

    @MockitoBean
    private RecipeService recipeService;

    @Test
    void returnsRecipeAsJson() {
        given(recipeService.findById(1)).willReturn(Optional.of(new Recipe(1, "Pancakes", 20)));

        assertThat(mvc.get().uri("/api/recipes/1"))
                .hasStatusOk()
                .bodyJson().extractingPath("$.name").isEqualTo("Pancakes");
    }

    @Test
    void unknownRecipeIs404() {
        given(recipeService.findById(99)).willReturn(Optional.empty());

        assertThat(mvc.get().uri("/api/recipes/99")).hasStatus(404);
    }
}

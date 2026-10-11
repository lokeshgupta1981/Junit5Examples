package com.howtodoinjava.junit.recipes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class RecipeApplicationTest {

    @Autowired
    private MockMvcTester mvc;

    @Test
    void contextLoadsAndServesRecipes() {
        assertThat(mvc.get().uri("/api/recipes/2"))
                .hasStatusOk()
                .bodyJson().extractingPath("$.minutes").isEqualTo(35);
    }
}

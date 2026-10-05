package com.howtodoinjava.assertj;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

@WebMvcTest(RecipeController.class)
class RecipeControllerTest {

  @Autowired
  MockMvcTester mvc;

  @MockitoBean
  RecipeBook recipeBook;

  @Test
  void returnsRecipe() {
    given(recipeBook.findByName("pancakes")).willReturn(Optional.of(Recipes.PANCAKES));

    assertThat(mvc.get().uri("/recipes/{name}", "pancakes"))
        .hasStatusOk()
        .hasContentType(MediaType.APPLICATION_JSON)
        .bodyJson()
        .extractingPath("$.minutes").isEqualTo(15);

    assertThat(mvc.get().uri("/recipes/{name}", "pancakes"))
        .bodyJson()
        .convertTo(Recipe.class)
        .satisfies(r -> assertThat(r.tags()).containsExactly("breakfast", "sweet"));

    assertThat(mvc.get().uri("/recipes/{name}", "pancakes"))
        .bodyJson()
        .isLenientlyEqualTo("""
            { "name": "Pancakes", "minutes": 15 }
            """);
  }

  @Test
  void returns404() {
    given(recipeBook.findByName("kiwi")).willReturn(Optional.empty());

    assertThat(mvc.get().uri("/recipes/{name}", "kiwi"))
        .hasStatus(HttpStatus.NOT_FOUND);
  }

  @Test
  void showBody() throws Exception {
    given(recipeBook.findByName("pancakes")).willReturn(Optional.of(Recipes.PANCAKES));
    String body = mvc.get().uri("/recipes/{name}", "pancakes").exchange()
        .getResponse().getContentAsString(java.nio.charset.StandardCharsets.UTF_8);
    System.out.println(body);
    assertThat(body).contains("\"added\":\"2026-09-12\"");
  }
}

package com.howtodoinjava.recipes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/** Fails on purpose: no @ExtendWith(MockitoExtension.class), so both fields stay null. */
@Tag("fails-on-purpose")
class MissingExtensionDemoTest {

  @Mock
  RecipeRepository recipeRepository;

  @InjectMocks
  RecipeService recipeService;

  @Test
  void fieldsAreNull() {
    when(recipeRepository.findByName("pancakes"))
        .thenReturn(Optional.of(new Recipe("pancakes", 20)));
    assertEquals(20, recipeService.cookingTime("pancakes"));
  }
}

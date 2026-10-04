package com.howtodoinjava.recipes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/** Fails on purpose: Mockito does not replace the final field, so the stub is ignored. */
@Tag("fails-on-purpose")
@ExtendWith(MockitoExtension.class)
class FinalFieldDemoTest {

  @Mock
  RecipeRepository recipeRepository;

  @InjectMocks
  FinalFieldService service;

  @Test
  void finalFieldKeepsRealRepository() {
    when(recipeRepository.findByName("pancakes"))
        .thenReturn(Optional.of(new Recipe("pancakes", 20)));
    assertEquals(20, service.cookingTime("pancakes"));
  }
}

package com.howtodoinjava.recipes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/** Initializing @Mock and @InjectMocks without MockitoExtension. */
class OpenMocksTest {

  @Mock
  RecipeRepository recipeRepository;

  @InjectMocks
  RecipeService recipeService;

  AutoCloseable mocks;

  @BeforeEach
  void openMocks() {
    mocks = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void closeMocks() throws Exception {
    mocks.close();
  }

  @Test
  void worksLikeTheExtension() {
    when(recipeRepository.findByName("pancakes"))
        .thenReturn(Optional.of(new Recipe("pancakes", 20)));
    assertEquals(20, recipeService.cookingTime("pancakes"));
  }

  @Test
  void noStrictStubsWithoutExtension() {
    when(recipeRepository.findByName("soup"))
        .thenReturn(Optional.of(new Recipe("soup", 15)));   // never used, test still passes
    assertEquals(20, 20);
  }
}

package com.howtodoinjava.recipes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

/** @Spy and @InjectMocks on the same field: a partial mock of the class under test. */
@ExtendWith(MockitoExtension.class)
class SpyOnInjectMocksTest {

  @Mock
  RecipeRepository recipeRepository;

  @Spy
  @InjectMocks
  RecipeService recipeService;

  @Test
  void stubOneMethodOfTheClassUnderTest() {
    doReturn(45).when(recipeService).cookingTime("curry");

    assertEquals(45, recipeService.cookingTime("curry"));
    verify(recipeService).cookingTime("curry");
    System.out.println("@Spy @InjectMocks -> " + recipeService.getClass().getSimpleName());
  }
}

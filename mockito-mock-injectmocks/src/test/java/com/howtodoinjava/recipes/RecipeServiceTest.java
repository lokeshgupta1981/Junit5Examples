package com.howtodoinjava.recipes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.misusing.NotAMockException;
import org.mockito.junit.jupiter.MockitoExtension;

/** The basic @Mock + @InjectMocks test shown at the top of the article. */
@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

  @Mock
  RecipeRepository recipeRepository;    // fake: returns what we stub

  @InjectMocks
  RecipeService recipeService;          // real: new RecipeService(recipeRepository)

  @Test
  void returnsCookingTimeFromRepository() {
    when(recipeRepository.findByName("pancakes"))
        .thenReturn(Optional.of(new Recipe("pancakes", 20)));

    int minutes = recipeService.cookingTime("pancakes");

    assertEquals(20, minutes);
    verify(recipeRepository).findByName("pancakes");
  }

  @Test
  void unknownRecipeThrows() {
    // findByName() is not stubbed: the mock returns Optional.empty()
    RecipeNotFoundException e = assertThrows(RecipeNotFoundException.class,
        () -> recipeService.cookingTime("soup"));
    assertEquals("Recipe soup not found", e.getMessage());
  }

  @Test
  void addRecipeSavesTrimmedName() {
    recipeService.addRecipe("  lasagna ", 90);
    verify(recipeRepository).save(new Recipe("lasagna", 90));
  }

  @Test
  void invalidMinutesNeverReachRepository() {
    assertThrows(IllegalArgumentException.class, () -> recipeService.addRecipe("tea", 0));
    verifyNoInteractions(recipeRepository);
  }

  @Test
  void verifyOnInjectMocksObjectFails() {
    NotAMockException e = assertThrows(NotAMockException.class, () -> verify(recipeService));
    System.out.println(e.getMessage());
  }

  @Test
  void mockAndInjectMocksAreDifferentKindsOfObjects() {
    System.out.println("@Mock        -> " + recipeRepository.getClass().getSimpleName());
    System.out.println("@InjectMocks -> " + recipeService.getClass().getSimpleName());
    assertEquals(RecipeService.class, recipeService.getClass());
  }
}

package com.howtodoinjava.recipes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/** How @InjectMocks picks constructor, setter or field injection. */
class InjectionStrategiesTest {

  @Nested
  @ExtendWith(MockitoExtension.class)
  class ConstructorInjection {

    @Mock
    RecipeRepository recipeRepository;

    @InjectMocks
    TwoConstructorService service;    // no @Mock TimeFormatter in this class

    @Test
    void biggestConstructorWinsAndMissingArgumentIsNull() {
      assertSame(recipeRepository, service.recipeRepository());
      assertNull(service.timeFormatter());         // passed as null, no error
    }
  }

  @Nested
  @ExtendWith(MockitoExtension.class)
  class SetterInjection {

    @Mock
    RecipeRepository recipeRepository;

    @InjectMocks
    SetterRecipeService service;

    @Test
    void mockArrivesThroughSetter() {
      when(recipeRepository.findByName("pancakes"))
          .thenReturn(Optional.of(new Recipe("pancakes", 20)));
      assertEquals(20, service.cookingTime("pancakes"));
    }
  }

  @Nested
  @ExtendWith(MockitoExtension.class)
  class FieldInjection {

    @Mock
    RecipeRepository recipeRepository;

    @InjectMocks
    FieldRecipeService service;

    @Test
    void mockArrivesThroughPrivateField() {
      when(recipeRepository.findByName("pancakes"))
          .thenReturn(Optional.of(new Recipe("pancakes", 20)));
      assertEquals(20, service.cookingTime("pancakes"));
    }
  }

  @Nested
  @ExtendWith(MockitoExtension.class)
  class RecordUnderTest {

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    TimeFormatter timeFormatter;

    @InjectMocks
    RecipePrinter printer;           // record: canonical constructor

    @Test
    void recordGetsMocksThroughCanonicalConstructor() {
      when(recipeRepository.findByName("lasagna"))
          .thenReturn(Optional.of(new Recipe("lasagna", 90)));
      when(timeFormatter.format(90)).thenReturn("90 minutes");

      assertEquals("lasagna: 90 minutes", printer.print("lasagna"));
      assertSame(recipeRepository, printer.recipeRepository());
    }
  }
}

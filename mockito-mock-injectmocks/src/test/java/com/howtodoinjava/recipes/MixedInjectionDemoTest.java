package com.howtodoinjava.recipes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

/** Fails on purpose: constructor injection runs, so the timeFormatter field is never set. */
@Tag("fails-on-purpose")
@ExtendWith(MockitoExtension.class)
class MixedInjectionDemoTest {

  @Mock
  RecipeRepository recipeRepository;

  @Spy
  TimeFormatter timeFormatter;

  @InjectMocks
  MixedInjectionService service;

  @Test
  void formatterFieldIsSkipped() {
    when(recipeRepository.findByName("lasagna"))
        .thenReturn(Optional.of(new Recipe("lasagna", 90)));
    assertEquals("lasagna (1 h 30 min)", service.menuLine("lasagna"));
  }
}

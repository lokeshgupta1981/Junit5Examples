package com.howtodoinjava.recipes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

/** @Spy fields are injected into @InjectMocks just like @Mock fields. */
@ExtendWith(MockitoExtension.class)
class SpyInjectionTest {

  @Mock
  RecipeRepository recipeRepository;   // fake

  @Spy
  TimeFormatter timeFormatter;         // real object, calls recorded

  @InjectMocks
  MenuService menuService;             // new MenuService(recipeRepository, timeFormatter)

  @Test
  void spyRunsRealCode() {
    when(recipeRepository.findByName("lasagna"))
        .thenReturn(Optional.of(new Recipe("lasagna", 90)));

    assertEquals("lasagna (1 h 30 min)", menuService.menuLine("lasagna"));
    verify(timeFormatter).format(90);
  }

  @Test
  void spyMethodCanBeStubbed() {
    when(recipeRepository.findByName("lasagna"))
        .thenReturn(Optional.of(new Recipe("lasagna", 90)));
    doReturn("long").when(timeFormatter).format(90);

    assertEquals("lasagna (long)", menuService.menuLine("lasagna"));
  }
}

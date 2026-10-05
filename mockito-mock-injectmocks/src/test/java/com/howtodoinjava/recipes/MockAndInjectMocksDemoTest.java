package com.howtodoinjava.recipes;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/** Fails on purpose: @Mock and @InjectMocks on the same field are rejected. */
@Tag("fails-on-purpose")
@ExtendWith(MockitoExtension.class)
class MockAndInjectMocksDemoTest {

  @Mock
  @InjectMocks
  RecipeService recipeService;

  @Test
  void neverRuns() {
  }
}

package com.howtodoinjava.assertj;

import org.assertj.core.api.AbstractAssert;

public class RecipeAssert extends AbstractAssert<RecipeAssert, Recipe> {

  private RecipeAssert(Recipe actual) {
    super(actual, RecipeAssert.class);
  }

  public static RecipeAssert assertThat(Recipe actual) {
    return new RecipeAssert(actual);
  }

  public RecipeAssert isQuick() {
    isNotNull();
    if (actual.minutes() > 20) {
      failWithMessage("Expected recipe <%s> to take at most 20 minutes but it takes <%d>",
          actual.name(), actual.minutes());
    }
    return this;
  }

  public RecipeAssert hasTag(String tag) {
    isNotNull();
    if (!actual.tags().contains(tag)) {
      failWithMessage("Expected recipe <%s> to have tag <%s> but tags were <%s>",
          actual.name(), tag, actual.tags());
    }
    return this;
  }
}

package com.howtodoinjava.assertj;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;

class ExceptionAssertionsTest {

  RecipeBook book = new RecipeBook().add(Recipes.PANCAKES);

  @Test
  void thrownBy() {
    assertThatThrownBy(() -> book.add(Recipes.PANCAKES))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("Recipe already exists: Pancakes")
        .hasNoCause();                                                 // passes
  }

  @Test
  void exceptionOfType() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> new Recipe("Toast", 0, List.of(), LocalDate.now()))
        .withMessage("Cooking time must be positive: 0");              // passes

    assertThatIllegalArgumentException()
        .isThrownBy(() -> new Recipe(" ", 5, List.of(), LocalDate.now()))
        .withMessageContaining("blank");                               // passes
  }

  @Test
  void catchingFirst() {
    Throwable thrown = catchThrowable(() -> Integer.parseInt("abc"));

    assertThat(thrown)
        .isInstanceOf(NumberFormatException.class)
        .hasMessage("For input string: \"abc\"");                     // passes

    IllegalStateException duplicate =
        catchThrowableOfType(IllegalStateException.class, () -> book.add(Recipes.PANCAKES));
    assertThat(duplicate.getMessage()).endsWith("Pancakes");           // passes

    Throwable none = catchThrowable(() -> book.add(Recipes.LASAGNA));
    assertThat(none).isNull();                                         // passes
  }

  @Test
  void noException() {
    assertThatCode(() -> book.findByName(null)).doesNotThrowAnyException();  // passes
    assertThatNoException().isThrownBy(() -> book.add(Recipes.OMELETTE));    // passes
  }

  @Test
  void missingException() {
    Throwable failure = catchThrowable(
        () -> assertThatThrownBy(() -> book.findByName("Pancakes"))
            .isInstanceOf(IllegalStateException.class));
    System.out.println(failure.getMessage());
    assertThat(failure).hasMessageContaining("Expecting code to raise a throwable");
  }
}

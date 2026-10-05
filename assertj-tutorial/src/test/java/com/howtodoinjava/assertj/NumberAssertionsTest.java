package com.howtodoinjava.assertj;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.byLessThan;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.offset;
import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.Assertions.withinPercentage;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class NumberAssertionsTest {

  @Test
  void integers() {
    int minutes = 15;

    assertThat(minutes).isEqualTo(15);                  // passes
    assertThat(minutes).isPositive().isOdd();           // passes
    assertThat(minutes).isGreaterThan(10).isLessThanOrEqualTo(20); // passes
    assertThat(minutes).isBetween(10, 20);              // passes, both ends included
    assertThat(minutes).isStrictlyBetween(14, 16);      // passes, both ends excluded
    assertThat(minutes).isCloseTo(16, within(1));       // passes
  }

  @Test
  void decimals() {
    double total = 0.1 + 0.2;                           // 0.30000000000000004

    Throwable exact = catchThrowable(() -> assertThat(total).isEqualTo(0.3));
    System.out.println(exact.getMessage());
    assertThat(exact).isInstanceOf(AssertionError.class);

    assertThat(total).isCloseTo(0.3, within(0.0001));   // passes
    assertThat(total).isCloseTo(0.3, offset(0.0001));   // passes, same as within()
    assertThat(total).isCloseTo(0.3, byLessThan(0.0001)); // passes
    assertThat(1.0).isCloseTo(1.1, within(0.1));        // passes, within() includes the bound
    Throwable strict = catchThrowable(() -> assertThat(1.0).isCloseTo(1.1, byLessThan(0.1)));
    System.out.println(strict.getMessage());
    assertThat(strict).isInstanceOf(AssertionError.class);

    assertThat(103.0).isCloseTo(100.0, withinPercentage(5)); // passes
    assertThat(new BigDecimal("2.50")).isEqualByComparingTo("2.5"); // passes
    assertThat(new BigDecimal("2.50")).isNotEqualTo(new BigDecimal("2.5")); // passes, scale differs
  }
}

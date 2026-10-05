package com.howtodoinjava.assertj;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.byLessThan;
import static org.assertj.core.api.Assertions.within;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Test;

class DateAssertionsTest {

  @Test
  void localDates() {
    LocalDate added = LocalDate.of(2026, 9, 12);

    assertThat(added).isBefore(LocalDate.of(2026, 10, 1));                       // passes
    assertThat(added).isAfterOrEqualTo("2026-09-12");                            // passes, ISO string
    assertThat(added).isBetween("2026-09-01", "2026-09-30");                     // passes
    assertThat(added).hasYear(2026).hasMonth(Month.SEPTEMBER).hasDayOfMonth(12); // passes
    assertThat(added).isInThePast();                                             // passes
  }

  @Test
  void timesAndDurations() {
    LocalDateTime start = LocalDateTime.of(2026, 10, 5, 18, 30, 0);
    LocalDateTime end = start.plusMinutes(15).plusSeconds(2);

    assertThat(end).isCloseTo(start.plusMinutes(15), within(5, ChronoUnit.SECONDS)); // passes
    assertThat(end).isCloseTo(start.plusMinutes(15), byLessThan(1, ChronoUnit.MINUTES)); // passes

    Instant createdAt = Instant.now();
    assertThat(createdAt).isCloseTo(Instant.now(), within(1, ChronoUnit.SECONDS)); // passes

    Duration cookingTime = Duration.between(start, end);
    assertThat(cookingTime).hasMinutes(15).isPositive();                          // passes
    assertThat(cookingTime).isCloseTo(Duration.ofMinutes(15), Duration.ofSeconds(5)); // passes
  }
}

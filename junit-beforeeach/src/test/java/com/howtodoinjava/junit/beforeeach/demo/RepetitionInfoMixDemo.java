package com.howtodoinjava.junit.beforeeach.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

/**
 * Fails on purpose: RepetitionInfo in @BeforeEach cannot be resolved for a plain @Test.
 * Run with: mvn test -Dtest=RepetitionInfoMixDemo
 */
class RepetitionInfoMixDemo {

  @BeforeEach
  void setUp(RepetitionInfo repetitionInfo) {
    System.out.println("repetition " + repetitionInfo.getCurrentRepetition());
  }

  @RepeatedTest(2)
  void repeated() {
  }

  @Test
  void plain() {
  }
}

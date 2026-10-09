package com.howtodoinjava.junit6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;

class JreConditionTest {

  // JUnit 5 code often used min = JRE.JAVA_11; JUnit 6 deprecates JAVA_8 to JAVA_16
  @Test
  @EnabledForJreRange(min = JRE.JAVA_21)
  void onJava21OrNewer() {
    System.out.println("Runs on Java 21 or newer");
  }

  @Test
  @EnabledOnJre(versions = 25)
  void onlyOnJava25() {
    System.out.println("Runs only on Java 25");
  }
}

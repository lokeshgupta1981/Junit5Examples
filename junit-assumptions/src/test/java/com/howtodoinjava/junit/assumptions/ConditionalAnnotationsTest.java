package com.howtodoinjava.junit.assumptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

/** The same checks as the assumption tests, written as conditional execution annotations. */
class ConditionalAnnotationsTest {

  @Test
  @EnabledOnOs(OS.LINUX)
  void runsOnLinux() {
    assertEquals("\n", System.lineSeparator());
  }

  @Test
  @EnabledIfEnvironmentVariable(named = "CI", matches = "true")
  void publishesReportOnCi() {
  }

  @Test
  @EnabledIfSystemProperty(named = "test.db.url", matches = "jdbc:.+")
  void runsWhenDatabaseUrlIsSet() {
  }

  @Test
  @DisabledOnJre(value = JRE.JAVA_25, disabledReason = "Known bug on Java 25")
  void notOnJava25() {
  }
}

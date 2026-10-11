package com.howtodoinjava.junit.backup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

class PropertyAndEnvConditionsTest {

  private final BackupService service = new BackupService();

  @Test
  @EnabledIfSystemProperty(named = "backup.target", matches = "s3://.+")
  void uploadsToS3() {
    assertTrue(service.target().startsWith("s3://"));
  }

  @Test
  @DisabledIfSystemProperty(named = "backup.target", matches = "s3://.+")
  void writesToLocalDisk() {
    assertEquals("local", service.target());
  }

  @Test
  @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
  @EnabledIfSystemProperty(named = "file.encoding", matches = "UTF-8")
  void runsOn64BitWithUtf8() {
    assertTrue(System.getProperty("os.arch").contains("64"));
  }

  @Test
  @EnabledIfEnvironmentVariable(named = "CI", matches = "true")
  void runsNightlyBackupOnCi() {
    assertEquals("true", System.getenv("CI"));
  }

  @Test
  @DisabledIfEnvironmentVariable(named = "CI", matches = "true", disabledReason = "Needs a local NAS share")
  void copiesToNasShare() {
    assertTrue(System.getenv("CI") == null || !System.getenv("CI").equals("true"));
  }
}

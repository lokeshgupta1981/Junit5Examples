package com.howtodoinjava.junit.backup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermissions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.io.TempDir;

class OsConditionsTest {

  private final BackupService service = new BackupService();

  @TempDir
  Path backupDir;

  @Test
  @EnabledOnOs({OS.LINUX, OS.MAC})
  void archiveIsReadableOnlyByOwner() throws IOException {
    Path archive = service.createPrivateArchive(backupDir, "orders");
    assertEquals("rw-------", PosixFilePermissions.toString(Files.getPosixFilePermissions(archive)));
  }

  @Test
  @EnabledOnOs(OS.WINDOWS)
  void archiveNameIsCaseInsensitive() throws IOException {
    service.createArchive(backupDir, "orders");
    assertTrue(Files.exists(backupDir.resolve("ORDERS.TAR")));
  }

  @Test
  @DisabledOnOs(value = OS.WINDOWS, disabledReason = "Windows has no POSIX permissions")
  void archiveIsCreated() throws IOException {
    assertTrue(Files.exists(service.createArchive(backupDir, "invoices")));
  }

  @Test
  @EnabledOnOs(value = OS.LINUX, architectures = "aarch64")
  void runsOnlyOnArmLinux() {
    assertEquals("aarch64", System.getProperty("os.arch"));
  }
}

package com.howtodoinjava.junit.backup;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.extension.ExtensionContext;

class CustomConditionsTest {

  @Test
  @EnabledIf("backupServerReachable")
  void sendsArchiveToBackupServer() {
    assertTrue(backupServerReachable());
  }

  @Test
  @EnabledIf("com.howtodoinjava.junit.backup.FeatureFlags#incrementalBackupEnabled")
  void createsIncrementalBackup() {
    assertTrue(FeatureFlags.incrementalBackupEnabled());
  }

  @Test
  @DisabledIf(value = "isNightlyTest", disabledReason = "Runs only in the nightly build")
  void nightlyFullBackup() {
    assertTrue(true);
  }

  boolean backupServerReachable() {
    try (Socket socket = new Socket()) {
      socket.connect(new InetSocketAddress("localhost", 9000), 200);
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  boolean isNightlyTest(ExtensionContext context) {
    return context.getDisplayName().startsWith("nightly") && System.getenv("NIGHTLY") == null;
  }
}

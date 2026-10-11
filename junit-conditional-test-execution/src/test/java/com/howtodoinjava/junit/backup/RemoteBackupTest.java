package com.howtodoinjava.junit.backup;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(BackupServerAvailable.class)
class RemoteBackupTest {

  @Test
  void uploadsArchive() {
    assertTrue(true);
  }
}

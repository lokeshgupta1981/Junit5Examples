package com.howtodoinjava.junit.backup;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledInNativeImage;

class LinuxBackupTest {

  @LinuxOnlyTest
  void usesTmpDirectory() {
    assertTrue(Files.isDirectory(Path.of("/tmp")));
  }

  @Test
  @DisabledInNativeImage
  void readsVersionWithReflection() throws ReflectiveOperationException {
    Object version = Runtime.class.getMethod("version").invoke(null);
    assertTrue(version.toString().startsWith("25"));
  }
}

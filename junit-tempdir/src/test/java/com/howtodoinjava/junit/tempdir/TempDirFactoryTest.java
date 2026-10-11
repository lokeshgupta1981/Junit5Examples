package com.howtodoinjava.junit.tempdir;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.api.io.TempDirDeletionStrategy;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TempDirFactoryTest {

    @Test
    void namesDirectoryAfterTest(@TempDir(factory = TestNameTempDirFactory.class) Path tempDir) {
        System.out.println("Factory directory: " + tempDir.getFileName());
        assertTrue(tempDir.getFileName().toString().startsWith("namesDirectoryAfterTest-"));
    }

    @Test
    void ignoresDeletionFailures(
            @TempDir(deletionStrategy = TempDirDeletionStrategy.IgnoreFailures.class) Path tempDir) {
        assertTrue(tempDir.toFile().isDirectory());
    }
}

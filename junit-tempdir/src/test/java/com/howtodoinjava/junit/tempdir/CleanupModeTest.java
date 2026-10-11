package com.howtodoinjava.junit.tempdir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CleanupModeTest {

    @Test
    void keepsDirectoryForInspection(@TempDir(cleanup = CleanupMode.NEVER) Path tempDir) throws IOException {
        Path report = Files.writeString(tempDir.resolve("report.txt"), "kept after the test");
        System.out.println("Kept: " + report);
        assertTrue(Files.exists(report));
    }
}

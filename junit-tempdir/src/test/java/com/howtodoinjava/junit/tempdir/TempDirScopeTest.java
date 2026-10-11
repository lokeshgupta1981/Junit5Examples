package com.howtodoinjava.junit.tempdir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TempDirScopeTest {

    @TempDir
    static Path sharedDir;

    @TempDir
    Path perTestDir;

    @TempDir
    File legacyDir;

    @Test
    @Order(1)
    void firstTest() throws IOException {
        System.out.println("firstTest  shared=" + sharedDir.getFileName() + " perTest=" + perTestDir.getFileName());
        Files.writeString(sharedDir.resolve("catalog.txt"), "written by firstTest");
        Files.writeString(perTestDir.resolve("draft.txt"), "written by firstTest");
        assertTrue(legacyDir.isDirectory());
    }

    @Test
    @Order(2)
    void secondTest() {
        System.out.println("secondTest shared=" + sharedDir.getFileName() + " perTest=" + perTestDir.getFileName());
        assertTrue(Files.exists(sharedDir.resolve("catalog.txt")));     // same directory as firstTest
        assertFalse(Files.exists(perTestDir.resolve("draft.txt")));     // new, empty directory
        assertNotEquals(perTestDir, legacyDir.toPath());                // every declaration is separate
    }
}

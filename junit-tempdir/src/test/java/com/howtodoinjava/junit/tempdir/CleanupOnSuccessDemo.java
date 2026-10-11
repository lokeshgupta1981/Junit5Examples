package com.howtodoinjava.junit.tempdir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Fails on purpose to show ON_SUCCESS; run with: mvn test -Dtest=CleanupOnSuccessDemo
class CleanupOnSuccessDemo {

    @Test
    void keepsFilesOfFailedTest(@TempDir(cleanup = CleanupMode.ON_SUCCESS) Path tempDir) throws IOException {
        Path export = Files.writeString(tempDir.resolve("export.csv"), "number,customer,amount");
        System.out.println("Export file: " + export);
        assertEquals(2, Files.readAllLines(export).size());
    }
}

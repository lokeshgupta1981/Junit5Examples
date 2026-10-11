package com.howtodoinjava.junit.tempdir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConstructorInjectionTest {

    private final Path archiveDir;
    private final ArchiveCleaner cleaner = new ArchiveCleaner();

    ConstructorInjectionTest(@TempDir Path archiveDir) {
        this.archiveDir = archiveDir;
    }

    @BeforeEach
    void createFiles() throws IOException {
        Files.createFile(archiveDir.resolve("upload-1.tmp"));
        Files.createFile(archiveDir.resolve("upload-2.tmp"));
        Files.createFile(archiveDir.resolve("report.pdf"));
    }

    @Test
    void deletesOnlyTmpFiles() throws IOException {
        List<String> deleted = cleaner.deleteTempFiles(archiveDir);

        assertEquals(List.of("upload-1.tmp", "upload-2.tmp"), deleted);
        assertEquals(List.of(archiveDir.resolve("report.pdf")), Files.list(archiveDir).toList());
    }
}

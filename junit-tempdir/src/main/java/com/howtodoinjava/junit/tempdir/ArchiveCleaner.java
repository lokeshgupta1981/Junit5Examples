package com.howtodoinjava.junit.tempdir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class ArchiveCleaner {

    // Deletes every *.tmp file in the directory and returns the names of the deleted files
    public List<String> deleteTempFiles(Path dir) throws IOException {
        try (Stream<Path> files = Files.list(dir)) {
            List<Path> tmpFiles = files.filter(f -> f.getFileName().toString().endsWith(".tmp")).toList();
            for (Path file : tmpFiles) {
                Files.delete(file);
            }
            return tmpFiles.stream().map(f -> f.getFileName().toString()).sorted().toList();
        }
    }
}

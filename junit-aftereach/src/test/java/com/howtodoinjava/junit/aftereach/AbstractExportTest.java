package com.howtodoinjava.junit.aftereach;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;

/**
 * Shared cleanup for all export tests. The superclass @AfterEach runs after the @AfterEach methods of subclasses.
 */
abstract class AbstractExportTest {

  protected static final Path EXPORT_DIR = Path.of("target", "exports");

  protected final InvoiceExporter exporter = new InvoiceExporter(EXPORT_DIR);

  @AfterEach
  void deleteExports() throws IOException {
    if (Files.notExists(EXPORT_DIR)) {
      return;
    }
    try (Stream<Path> files = Files.list(EXPORT_DIR)) {
      for (Path file : files.toList()) {
        Files.delete(file);
      }
    }
    System.out.println("2. AbstractExportTest.deleteExports()");
  }

  protected long countExports() throws IOException {
    try (Stream<Path> files = Files.list(EXPORT_DIR)) {
      return files.count();
    }
  }
}

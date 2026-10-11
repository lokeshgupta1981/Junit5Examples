package com.howtodoinjava.junit.perclass;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.io.TempDir;

/**
 * The quick example: with PER_CLASS, @BeforeAll and @AfterAll are instance methods and use instance fields.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookCatalogTest {

  static int instances = 0;

  BookCatalog catalog;
  int lookups = 0;

  BookCatalogTest() {
    instances++;
    System.out.println("constructor: test instance #" + instances);
  }

  @BeforeAll
  void loadCatalog(@TempDir Path dir) throws IOException {
    Path file = dir.resolve("books.csv");
    Files.write(file, List.of("Dune,Frank Herbert", "Emma,Jane Austen", "Persuasion,Jane Austen"));
    catalog = BookCatalog.load(file);
    System.out.println("@BeforeAll loadCatalog(): " + catalog.size() + " books");
  }

  @Test
  void findsAustenBooks() {
    lookups++;
    assertEquals(List.of("Emma", "Persuasion"), catalog.titlesBy("Jane Austen"));
  }

  @Test
  void findsHerbertBook() {
    lookups++;
    assertEquals(List.of("Dune"), catalog.titlesBy("Frank Herbert"));
  }

  @AfterAll
  void printStats() {
    System.out.println("@AfterAll printStats(): " + lookups + " lookups on test instance #" + instances);
  }
}

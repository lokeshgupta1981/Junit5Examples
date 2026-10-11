package com.howtodoinjava.junit.perclass;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * With PER_CLASS, a @MethodSource factory method can be an instance method and read the catalog that @BeforeAll loaded.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthorSearchTest {

  BookCatalog catalog;

  @BeforeAll
  void loadCatalog(@TempDir Path dir) throws IOException {
    Path file = dir.resolve("books.csv");
    Files.write(file, List.of("Emma,Jane Austen", "Persuasion,Jane Austen", "Dune,Frank Herbert"));
    catalog = BookCatalog.load(file);
  }

  Stream<String> austenTitles() {                       // non-static factory method
    return catalog.titlesBy("Jane Austen").stream();
  }

  @ParameterizedTest
  @MethodSource("austenTitles")
  void titleIsNotBlank(String title) {
    System.out.println("@ParameterizedTest with " + title);
    assertTrue(!title.isBlank());
  }
}

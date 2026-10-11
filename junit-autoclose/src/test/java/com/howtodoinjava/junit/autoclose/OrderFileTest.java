package com.howtodoinjava.junit.autoclose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * A field assigned in @BeforeEach is closed after the test, the same as an initialized field.
 */
class OrderFileTest {

  @TempDir
  Path dir;

  @AutoClose
  Stream<String> lines;                                  // Files.lines() keeps the file open

  @BeforeEach
  void openOrderFile() throws IOException {
    Path file = dir.resolve("orders.csv");
    Files.write(file, List.of("apple,3", "banana,5", "apple,1"));
    lines = Files.lines(file);
  }

  @Test
  void countsAppleOrders() {
    long appleOrders = lines.filter(line -> line.startsWith("apple")).count();
    assertEquals(2, appleOrders);
  }
}

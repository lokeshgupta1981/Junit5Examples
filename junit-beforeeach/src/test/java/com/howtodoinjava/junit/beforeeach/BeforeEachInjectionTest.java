package com.howtodoinjava.junit.beforeeach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.io.TempDir;

/**
 * A @BeforeEach method can declare parameters. JUnit resolves TestInfo and @TempDir Path for each test.
 */
class BeforeEachInjectionTest {

  Path playlistFile;

  @BeforeEach
  void writePlaylistFile(TestInfo testInfo, @TempDir Path tempDir) throws IOException {
    playlistFile = tempDir.resolve("playlist.txt");
    Files.write(playlistFile, List.of("Intro", "Outro"));
    System.out.println("Setting up '" + testInfo.getDisplayName() + "' in " + tempDir.getFileName());
  }

  @Test
  @DisplayName("reads two songs")
  void readsSongs() throws IOException {
    assertEquals(List.of("Intro", "Outro"), Files.readAllLines(playlistFile));
  }

  @Test
  @DisplayName("appends a song")
  void appendsSong() throws IOException {
    Files.writeString(playlistFile, "Encore\n", java.nio.file.StandardOpenOption.APPEND);
    assertEquals(3, Files.readAllLines(playlistFile).size());
    assertTrue(Files.exists(playlistFile));
  }
}

package com.howtodoinjava.junit.beforeeach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The superclass @BeforeEach runs first, so the playlist already exists when addRockSongs() runs.
 * For @AfterEach the order is reversed: the subclass method runs first.
 */
class RockPlaylistTest extends BasePlaylistTest {

  @BeforeEach
  void addRockSongs() {
    playlist.add("Thunder");                   // playlist is not null here
    System.out.println("2. RockPlaylistTest.addRockSongs()");
  }

  @Test
  void hasRockSong() {
    System.out.println("3. @Test hasRockSong");
    assertEquals(List.of("Thunder"), playlist.songs());
  }

  @AfterEach
  void afterRock() {
    System.out.println("4. RockPlaylistTest.afterRock()");
  }
}

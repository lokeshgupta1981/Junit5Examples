package com.howtodoinjava.junit.beforeeach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

/**
 * @BeforeEach runs before every repetition of a @RepeatedTest. RepetitionInfo can be injected into @BeforeEach
 * only when every test in the class is a @RepeatedTest.
 */
class RepeatedPlaylistTest {

  Playlist playlist;

  @BeforeEach
  void createPlaylist(RepetitionInfo repetitionInfo) {
    playlist = new Playlist("repeat");
    System.out.println("@BeforeEach for repetition " + repetitionInfo.getCurrentRepetition()
        + " of " + repetitionInfo.getTotalRepetitions());
  }

  @RepeatedTest(3)
  void addSong() {
    playlist.add("Loop");
    assertEquals(1, playlist.size());       // always 1, the playlist is new each time
  }
}

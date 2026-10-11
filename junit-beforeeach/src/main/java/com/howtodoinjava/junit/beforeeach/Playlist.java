package com.howtodoinjava.junit.beforeeach;

import java.util.ArrayList;
import java.util.List;

/**
 * A small mutable playlist. Every test that changes it needs a fresh copy, which is what @BeforeEach gives us.
 */
public class Playlist {

  private final String name;
  private final List<String> songs = new ArrayList<>();

  public Playlist(String name) {
    this.name = name;
  }

  public void add(String song) {
    songs.add(song);
  }

  public boolean remove(String song) {
    return songs.remove(song);
  }

  public int size() {
    return songs.size();
  }

  public List<String> songs() {
    return List.copyOf(songs);
  }

  public String name() {
    return name;
  }
}

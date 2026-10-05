package com.howtodoinjava.junit5.examples.exceptions;

/**
 * A checked exception thrown when a playlist file cannot be read.
 */
public class PlaylistException extends Exception {

  public PlaylistException(String message, Throwable cause) {
    super(message, cause);
  }
}

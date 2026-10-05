package com.howtodoinjava.mockito;

import java.util.UUID;

public final class LoanIds {

  private LoanIds() {
  }

  public static String next() {
    return UUID.randomUUID().toString();
  }
}

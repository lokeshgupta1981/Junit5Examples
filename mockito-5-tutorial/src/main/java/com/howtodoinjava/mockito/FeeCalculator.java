package com.howtodoinjava.mockito;

// A final class. Mockito 5 can mock and spy it with the default inline mock maker.
public final class FeeCalculator {

  public int perDay() {
    return 2;
  }

  public int lateFee(int daysLate) {
    return daysLate * perDay();
  }
}

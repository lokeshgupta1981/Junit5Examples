package com.howtodoinjava.junit.vintage;

public class DiscountService {

  public int percentFor(int loyaltyYears) {
    if (loyaltyYears >= 5) {
      return 15;
    }
    return loyaltyYears >= 2 ? 5 : 0;
  }
}

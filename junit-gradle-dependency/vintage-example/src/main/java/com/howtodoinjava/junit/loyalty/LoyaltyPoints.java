package com.howtodoinjava.junit.loyalty;

public class LoyaltyPoints {

  public int forOrder(int orderTotalDollars, boolean member) {
    if (orderTotalDollars < 0) {
      throw new IllegalArgumentException("Order total must not be negative");
    }
    int points = orderTotalDollars / 10;
    return member ? points * 2 : points;
  }
}

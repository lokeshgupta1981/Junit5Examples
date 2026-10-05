package com.howtodoinjava.legacy.billing;

import com.howtodoinjava.legacy.pricing.PriceCalculator;

public record Invoice(int items) {

  public int total() {
    return new PriceCalculator().price(this);
  }
}

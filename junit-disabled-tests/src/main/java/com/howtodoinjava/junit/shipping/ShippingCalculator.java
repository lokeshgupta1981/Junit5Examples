package com.howtodoinjava.junit.shipping;

public class ShippingCalculator {

  public int domesticRate(int weightInGrams) {
    if (weightInGrams <= 0) {
      throw new IllegalArgumentException("weight must be positive");
    }
    return weightInGrams <= 1000 ? 499 : 499 + (weightInGrams - 1000) / 500 * 150;
  }

  public int internationalRate(int weightInGrams, String countryCode) {
    // Known bug #142: the surcharge for non-EU countries is not applied yet
    return domesticRate(weightInGrams) * 3;
  }
}

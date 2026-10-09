package com.howtodoinjava.junit6;

import java.text.NumberFormat;
import java.util.Locale;

public class PriceCalculator {

  public double total(int quantity, double unitPrice) {
    if (quantity < 0) {
      throw new IllegalArgumentException("Quantity must not be negative: " + quantity);
    }
    return quantity * unitPrice;
  }

  public String format(double amount) {
    return NumberFormat.getCurrencyInstance(Locale.getDefault()).format(amount);
  }
}

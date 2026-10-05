package com.howtodoinjava.legacy.pricing;

import com.howtodoinjava.legacy.billing.Invoice;

public class PriceCalculator {

  public int price(Invoice invoice) {
    return invoice.items() * 5;
  }
}

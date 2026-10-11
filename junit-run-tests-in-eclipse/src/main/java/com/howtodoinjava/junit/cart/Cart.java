package com.howtodoinjava.junit.cart;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {

  private final Map<String, Integer> quantities = new LinkedHashMap<>();

  public void add(String item, int quantity) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("Quantity must be positive");
    }
    quantities.merge(item, quantity, Integer::sum);
  }

  public int quantityOf(String item) {
    return quantities.getOrDefault(item, 0);
  }

  public int itemCount() {
    return quantities.values().stream().mapToInt(Integer::intValue).sum();
  }

  public boolean isEmpty() {
    return quantities.isEmpty();
  }
}

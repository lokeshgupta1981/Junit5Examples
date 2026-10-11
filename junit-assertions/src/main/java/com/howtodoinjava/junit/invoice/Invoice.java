package com.howtodoinjava.junit.invoice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Invoice {

  private final String number;
  private final Customer customer;
  private final LocalDate date;
  private final List<String> items = new ArrayList<>();
  private final List<Integer> quantities = new ArrayList<>();
  private final List<Double> prices = new ArrayList<>();
  private String discountCode;

  public Invoice(String number, Customer customer, LocalDate date) {
    this.number = number;
    this.customer = customer;
    this.date = date;
  }

  public Invoice addItem(String name, int quantity, double price) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("quantity must be positive: " + quantity);
    }
    items.add(name);
    quantities.add(quantity);
    prices.add(price);
    return this;
  }

  public String number() {
    return number;
  }

  public Customer customer() {
    return customer;
  }

  public int itemCount() {
    return items.size();
  }

  public List<String> itemNames() {
    return List.copyOf(items);
  }

  public int[] quantities() {
    return quantities.stream().mapToInt(Integer::intValue).toArray();
  }

  public double total() {
    double total = 0;
    for (int i = 0; i < items.size(); i++) {
      total += quantities.get(i) * prices.get(i);
    }
    return total;
  }

  public boolean isEmpty() {
    return items.isEmpty();
  }

  public String discountCode() {
    return discountCode;
  }

  public void applyDiscount(String code) {
    this.discountCode = code;
  }

  public List<String> print() {
    List<String> lines = new ArrayList<>();
    lines.add("INVOICE " + number);
    lines.add("Date: " + date);
    lines.add("Customer: " + customer.name());
    for (int i = 0; i < items.size(); i++) {
      lines.add(String.format(Locale.ROOT, "%-8s x%d %8.2f", items.get(i), quantities.get(i), prices.get(i)));
    }
    lines.add(String.format(Locale.ROOT, "Total: %.2f", total()));
    return lines;
  }
}

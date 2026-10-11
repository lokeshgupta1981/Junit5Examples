package com.howtodoinjava.junit.invoices;

import java.math.BigDecimal;

public class InvoiceApplication {

  public static void main(String[] args) {
    InvoiceCalculator calculator = new InvoiceCalculator();
    System.out.println(calculator.total(new BigDecimal("640.00"), false));
  }
}

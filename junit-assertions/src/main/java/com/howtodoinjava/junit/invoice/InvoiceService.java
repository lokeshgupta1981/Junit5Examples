package com.howtodoinjava.junit.invoice;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.LockSupport;

public class InvoiceService {

  private final Map<String, Invoice> cache = new HashMap<>();
  private final Duration exportDelay;

  public InvoiceService() {
    this(Duration.ZERO);
  }

  public InvoiceService(Duration exportDelay) {
    this.exportDelay = exportDelay;
  }

  public Invoice get(String number) {
    return cache.computeIfAbsent(number,
        n -> new Invoice(n, new Customer("Lokesh", "lokesh@example.com"), LocalDate.of(2026, 10, 11)));
  }

  public Invoice copyOf(Invoice invoice) {
    return new Invoice(invoice.number(), invoice.customer(), LocalDate.of(2026, 10, 11));
  }

  public PaymentMethod paymentMethodFor(String type) {
    return switch (type) {
      case "card" -> new Card("4242");
      case "bank" -> new BankTransfer("DE89370400440532013000");
      default -> throw new IllegalArgumentException("Unknown payment type: " + type);
    };
  }

  public String exportPdf(Invoice invoice) {
    long end = System.nanoTime() + exportDelay.toNanos();
    while (System.nanoTime() < end) {
      LockSupport.parkNanos(Duration.ofMillis(10).toNanos());
      if (Thread.currentThread().isInterrupted()) {
        throw new IllegalStateException("PDF export interrupted");
      }
    }
    return invoice.number() + ".pdf";
  }
}

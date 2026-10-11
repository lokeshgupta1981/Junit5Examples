package com.howtodoinjava.junit.invoice;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;

// Fails on purpose to show the failure messages. Not matched by the default Surefire includes;
// run it with: mvn test -Dtest=AssertionsFailureDemo
class AssertionsFailureDemo {

  private final Invoice invoice = new Invoice("2026-001", new Customer("Lokesh", "lokesh@example.com"),
      LocalDate.of(2026, 10, 11)).addItem("pen", 3, 1.10).addItem("notebook", 2, 4.50);

  @Test
  void wrongTotal() {
    Invoice erasers = new Invoice("2026-003", new Customer("Lokesh", "lokesh@example.com"), LocalDate.of(2026, 10, 11))
        .addItem("eraser", 3, 0.10);
    assertEquals(0.3, erasers.total(), "invoice total");
  }

  @Test
  void groupReportsEveryFailure() {
    assertAll("invoice",
        () -> assertEquals("2026-002", invoice.number()),
        () -> assertEquals(3, invoice.itemCount()),
        () -> assertEquals("Lokesh", invoice.customer().name()));
  }

  @Test
  void iterableDiffers() {
    assertIterableEquals(List.of("pen", "ink"), invoice.itemNames());
  }

  @Test
  void linesDiffer() {
    assertLinesMatch(List.of("INVOICE 2026-001", ">> item lines >>", "Total: 12.00"), invoice.print());
  }

  @Test
  void slowExportWaits() {
    InvoiceService slowService = new InvoiceService(Duration.ofMillis(500));
    String file = assertTimeout(Duration.ofMillis(100), () -> slowService.exportPdf(invoice));
  }

  @Test
  void slowExportIsStopped() {
    InvoiceService slowService = new InvoiceService(Duration.ofMillis(500));
    String file = assertTimeoutPreemptively(Duration.ofMillis(100), () -> slowService.exportPdf(invoice));
  }

  @Test
  void notWrittenYet() {
    fail("test for credit notes is not written yet");
  }
}

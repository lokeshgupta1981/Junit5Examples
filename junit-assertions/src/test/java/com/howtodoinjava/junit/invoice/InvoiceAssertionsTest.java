package com.howtodoinjava.junit.invoice;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InvoiceAssertionsTest {

  private final InvoiceService service = new InvoiceService();
  private Invoice invoice;

  @BeforeEach
  void createInvoice() {
    invoice = new Invoice("2026-001", new Customer("Lokesh", "lokesh@example.com"), LocalDate.of(2026, 10, 11))
        .addItem("pen", 3, 1.10)
        .addItem("notebook", 2, 4.50);
  }

  @Test
  void quickReference() {
    assertEquals(2, invoice.itemCount());                                   // passes
    assertEquals(12.3, invoice.total(), 0.001);                             // passes, 12.3 within 0.001
    assertNotEquals(0, invoice.itemCount());                                // passes
    assertTrue(invoice.total() > 10);                                       // passes
    assertFalse(invoice.isEmpty());                                         // passes
    assertNull(invoice.discountCode());                                     // passes, no discount yet
    assertNotNull(invoice.customer());                                      // passes
    assertSame(service.get("2026-002"), service.get("2026-002"));           // passes, same cached object
    assertArrayEquals(new int[] {3, 2}, invoice.quantities());              // passes
    assertIterableEquals(List.of("pen", "notebook"), invoice.itemNames());  // passes
    Card card = assertInstanceOf(Card.class, service.paymentMethodFor("card"));   // passes, returns the Card
    assertAll("invoice",
        () -> assertEquals("2026-001", invoice.number()),
        () -> assertEquals("Lokesh", invoice.customer().name()));          // passes, both checks ran
    assertThrows(IllegalArgumentException.class, () -> invoice.addItem("pen", 0, 1.10));   // passes
    String file = assertTimeout(Duration.ofSeconds(1), () -> service.exportPdf(invoice));    // 2026-001.pdf
  }

  @Test
  void equality() {
    double total = invoice.total();                                         // 12.3 (3 x 1.10 + 2 x 4.50)
    double penTotal = 3 * 1.10;                                             // 3.3000000000000003

    assertEquals(2, invoice.itemCount());
    assertEquals("Lokesh", invoice.customer().name(), "customer name");
    assertEquals(3.3, penTotal, 0.001);
    assertEquals(12.3, total, 0.001);
    assertNotEquals("2026-002", invoice.number());
    Invoice erasers = new Invoice("2026-003", new Customer("Lokesh", "lokesh@example.com"), LocalDate.of(2026, 10, 11))
        .addItem("eraser", 3, 0.10);
    double erasersTotal = erasers.total();                                  // 0.30000000000000004
    assertEquals(0.3, erasersTotal, 0.001);                                 // passes within the delta
    assertEquals(new Customer("Lokesh", "lokesh@example.com"), invoice.customer());   // passes, records compare fields
  }

  @Test
  void messages() {
    assertEquals(2, invoice.itemCount(), "item count");
    assertEquals(2, invoice.itemCount(), () -> "item count of " + invoice.print());
  }

  @Test
  void conditionsAndNulls() {
    assertTrue(invoice.total() > 10, "total above 10");
    assertTrue(() -> invoice.itemNames().contains("pen"));
    assertFalse(invoice.isEmpty());
    assertNull(invoice.discountCode());
    invoice.applyDiscount("AUTUMN10");
    assertNotNull(invoice.discountCode());
  }

  @Test
  void sameAndNotSame() {
    Invoice first = service.get("2026-002");
    Invoice second = service.get("2026-002");
    Invoice copy = service.copyOf(first);

    assertSame(first, second);                                              // passes, cache returns one object
    assertNotSame(first, copy);                                             // passes, copyOf() creates a new object
  }

  @Test
  void arraysIterablesAndLines() {
    assertArrayEquals(new int[] {3, 2}, invoice.quantities());
    assertArrayEquals(new double[] {3.3, 9.0}, new double[] {3 * 1.10, 2 * 4.50}, 0.001);
    assertIterableEquals(List.of("pen", "notebook"), invoice.itemNames());
    assertIterableEquals(List.of(List.of("pen"), List.of("ink")), List.of(List.of("pen"), List.of("ink")));

    List<String> expected = List.of(
        "INVOICE 2026-001",
        "Date: \\d{4}-\\d{2}-\\d{2}",
        ">> item lines >>",
        "Total: 12.30");
    assertLinesMatch(expected, invoice.print());
    assertLinesMatch(List.of("INVOICE 2026-001", ">> 4 >>", "Total: 12.30"), invoice.print());
  }

  @Test
  void streamOfLines() {
    assertLinesMatch(Stream.of("INVOICE .*", ">>>>"), invoice.print().stream());
  }

  @Test
  void typeCheck() {
    PaymentMethod method = service.paymentMethodFor("card");

    Card card = assertInstanceOf(Card.class, method);
    assertEquals("4242", card.lastDigits());
  }

  @Test
  void groupedAssertions() {
    assertAll("invoice",
        () -> assertEquals("2026-001", invoice.number()),
        () -> assertEquals(2, invoice.itemCount()),
        () -> assertEquals(12.3, invoice.total(), 0.001));
  }

  @Test
  void dependentAssertions() {
    assertAll("customer",
        () -> {
          Customer customer = invoice.customer();
          assertNotNull(customer);
          assertAll("customer fields",
              () -> assertEquals("Lokesh", customer.name()),
              () -> assertTrue(customer.email().endsWith("@example.com")));
        },
        () -> assertEquals(2, invoice.itemCount()));
  }

  @Test
  void exceptions() {
    IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
        () -> invoice.addItem("pen", 0, 1.10));
    String message = thrown.getMessage();                                   // quantity must be positive: 0

    PaymentMethod method = assertDoesNotThrow(() -> service.paymentMethodFor("bank"));
    assertEquals("quantity must be positive: 0", message);
    assertInstanceOf(BankTransfer.class, method);
  }

  @Test
  void timeouts() {
    String file = assertTimeout(Duration.ofSeconds(1), () -> service.exportPdf(invoice));
    String again = assertTimeoutPreemptively(Duration.ofSeconds(1), () -> service.exportPdf(invoice));
    assertEquals("2026-001.pdf", file);
    assertEquals(file, again);
  }

  @Test
  void failInBranch() {
    PaymentMethod method = service.paymentMethodFor("bank");
    String label = switch (method) {
      case Card c -> "card ending " + c.lastDigits();
      case BankTransfer b -> "bank transfer";
    };
    assertEquals("bank transfer", label);

    String first = invoice.itemNames().stream()
        .findFirst()
        .orElseGet(() -> fail("invoice has no items"));                    // fail() returns any type
    assertEquals("pen", first);
  }
}

package com.howtodoinjava.junit.aftereach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * The subclass @AfterEach runs first, so it can still see the exported files before the superclass deletes them.
 */
class InvoiceExportTest extends AbstractExportTest {

  @Test
  void exportsOneInvoice() throws IOException {
    exporter.export("alex", new BigDecimal("120.00"));
    assertEquals(1, countExports());
  }

  @Test
  void exportsTwoInvoices() throws IOException {
    exporter.export("alex", new BigDecimal("120.00"));
    exporter.export("maria", new BigDecimal("80.50"));
    assertEquals(2, countExports());
  }

  @AfterEach
  void logExports(TestInfo testInfo) throws IOException {
    System.out.println("1. " + testInfo.getDisplayName() + " exported " + countExports() + " file(s)");
  }
}

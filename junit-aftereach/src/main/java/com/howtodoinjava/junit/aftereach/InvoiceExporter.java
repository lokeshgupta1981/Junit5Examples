package com.howtodoinjava.junit.aftereach;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Writes one CSV file per invoice into the export folder of the app.
 */
public class InvoiceExporter {

  private final Path exportDir;

  public InvoiceExporter(Path exportDir) {
    this.exportDir = exportDir;
  }

  public Path export(String customer, BigDecimal amount) throws IOException {
    Files.createDirectories(exportDir);
    Path file = exportDir.resolve(customer + ".csv");
    return Files.writeString(file, customer + "," + amount.toPlainString() + System.lineSeparator());
  }
}

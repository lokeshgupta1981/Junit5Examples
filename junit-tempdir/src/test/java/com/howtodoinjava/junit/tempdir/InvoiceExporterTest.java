package com.howtodoinjava.junit.tempdir;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvoiceExporterTest {

    private final InvoiceExporter exporter = new InvoiceExporter();

    @Test
    void writesCsvFile(@TempDir Path tempDir) throws IOException {
        List<Invoice> invoices = List.of(
                new Invoice("1001", "Alex", new BigDecimal("120.50")),
                new Invoice("1002", "Brian", new BigDecimal("75.00")));

        Path csv = exporter.exportCsv(invoices, tempDir);

        System.out.println("Exported to " + csv);
        assertEquals(List.of("number,customer,amount", "1001,Alex,120.50", "1002,Brian,75.00"),
                Files.readAllLines(csv));
    }
}

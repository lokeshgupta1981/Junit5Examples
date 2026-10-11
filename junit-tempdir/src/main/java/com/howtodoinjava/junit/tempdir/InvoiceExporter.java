package com.howtodoinjava.junit.tempdir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class InvoiceExporter {

    public Path exportCsv(List<Invoice> invoices, Path targetDir) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("number,customer,amount");
        for (Invoice invoice : invoices) {
            lines.add(invoice.number() + "," + invoice.customer() + "," + invoice.amount());
        }
        Path file = targetDir.resolve("invoices.csv");
        return Files.write(file, lines);
    }
}

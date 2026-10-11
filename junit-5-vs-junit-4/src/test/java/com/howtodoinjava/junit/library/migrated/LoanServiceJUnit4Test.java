package com.howtodoinjava.junit.library.migrated;

import com.howtodoinjava.junit.library.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoanServiceJUnit4Test {

    @TempDir
    public File tempFolder;

    private LoanService service;

    @BeforeEach
    public void setUp() {
        service = new LoanService();
    }

    @Test
    public void borrowMarksBookAsOnLoan() {
        service.borrow("978-0134685991", "lokesh");
        assertEquals(1, service.openLoans(), "one open loan expected");
    }

    @Test
    public void borrowingTwiceFails() {
        assertThrows(IllegalStateException.class, () -> {
            service.borrow("978-0134685991", "lokesh");
            service.borrow("978-0134685991", "alex");
        });
    }

    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    public void lateFeeIsFast() {
        assertEquals(75, service.lateFee(3));
    }

    @Test
    @Tag("SlowTests")
    public void writesLoanReport() throws IOException {
        File report = newFile(tempFolder, "loans.csv");
        assertTrue(report.exists());
    }

    @Test
    @Disabled("Waiting for the reservation feature")
    public void reservedBookCannotBeBorrowed() {
    }

    private static File newFile(File parent, String child) throws IOException {
        File result = new File(parent, child);
        result.createNewFile();
        return result;
    }
}

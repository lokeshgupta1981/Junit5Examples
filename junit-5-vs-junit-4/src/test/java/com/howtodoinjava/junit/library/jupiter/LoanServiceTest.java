package com.howtodoinjava.junit.library.jupiter;

import com.howtodoinjava.junit.library.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoanServiceTest {

    @TempDir
    Path tempDir;

    private LoanService service;

    @BeforeEach
    void setUp() {
        service = new LoanService();
    }

    @Test
    void borrowMarksBookAsOnLoan() {
        service.borrow("978-0134685991", "lokesh");
        assertEquals(1, service.openLoans(), "one open loan expected");
    }

    @Test
    void borrowingTwiceFails() {
        service.borrow("978-0134685991", "lokesh");

        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> service.borrow("978-0134685991", "alex"));
        assertEquals("Book already on loan: 978-0134685991", ex.getMessage());
    }

    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void lateFeeIsFast() {
        assertEquals(75, service.lateFee(3));
    }

    @Test
    @Tag("slow")
    void writesLoanReport() throws IOException {
        Path report = Files.createFile(tempDir.resolve("loans.csv"));
        assertTrue(Files.exists(report));
    }

    @Test
    @DisplayName("Returning a book closes the loan")
    void giveBackClosesLoan() {
        service.borrow("978-0134685991", "lokesh");
        service.giveBack("978-0134685991");

        assertAll(
                () -> assertFalse(service.isOnLoan("978-0134685991")),
                () -> assertEquals(0, service.openLoans()));
    }

    @Test
    @Disabled("Waiting for the reservation feature")
    void reservedBookCannotBeBorrowed() {
    }
}

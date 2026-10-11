package com.howtodoinjava.junit.library.junit4;

import com.howtodoinjava.junit.library.LoanService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoanServiceJUnit4Test {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    private LoanService service;

    @Before
    public void setUp() {
        service = new LoanService();
    }

    @Test
    public void borrowMarksBookAsOnLoan() {
        service.borrow("978-0134685991", "lokesh");
        assertEquals("one open loan expected", 1, service.openLoans());
    }

    @Test(expected = IllegalStateException.class)
    public void borrowingTwiceFails() {
        service.borrow("978-0134685991", "lokesh");
        service.borrow("978-0134685991", "alex");
    }

    @Test(timeout = 500)
    public void lateFeeIsFast() {
        assertEquals(75, service.lateFee(3));
    }

    @Test
    @Category(SlowTests.class)
    public void writesLoanReport() throws IOException {
        File report = tempFolder.newFile("loans.csv");
        assertTrue(report.exists());
    }

    @Test
    @Ignore("Waiting for the reservation feature")
    public void reservedBookCannotBeBorrowed() {
    }
}

package com.howtodoinjava.junit.booking;

import com.howtodoinjava.junit.listener.FailureReportWatcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Fails on purpose to show the listener and watcher output. Excluded from mvn test, run with -Dtest=BookingFailingTest
@ExtendWith(FailureReportWatcher.class)
class BookingFailingTest {

    @Test
    void weekendPriceWithWrongExpectation() {
        BookingService service = new BookingService(120);
        assertEquals(300, service.price(LocalDate.of(2026, 7, 3), LocalDate.of(2026, 7, 5)));
    }
}

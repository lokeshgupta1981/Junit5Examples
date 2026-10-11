package com.howtodoinjava.junit.booking;

import com.howtodoinjava.junit.listener.FailureReportWatcher;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(FailureReportWatcher.class)
class BookingServiceTest {

    private final BookingService service = new BookingService(120);

    @Test
    void priceOfThreeNights() {
        assertEquals(360, service.price(LocalDate.of(2026, 7, 1), LocalDate.of(2026, 7, 4)));
    }

    @Test
    void sameDayCheckOutIsRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> service.price(LocalDate.of(2026, 7, 1), LocalDate.of(2026, 7, 1)));
    }

    @Test
    @Disabled("Seasonal rates are not implemented yet")
    void highSeasonRate() {
    }
}

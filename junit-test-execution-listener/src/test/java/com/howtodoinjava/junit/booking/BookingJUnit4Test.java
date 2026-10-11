package com.howtodoinjava.junit.booking;

import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

// Run by RunJUnit4WithListener through JUnitCore, excluded from mvn test because one test fails on purpose
public class BookingJUnit4Test {

    private final BookingService service = new BookingService(120);

    @Test
    public void priceOfOneNight() {
        assertEquals(120, service.price(LocalDate.of(2026, 7, 1), LocalDate.of(2026, 7, 2)));
    }

    @Test
    public void priceOfTwoNightsIsWrong() {
        assertEquals(200, service.price(LocalDate.of(2026, 7, 1), LocalDate.of(2026, 7, 3)));
    }

    @Test
    @Ignore("Seasonal rates are not implemented yet")
    public void highSeasonRate() {
    }
}

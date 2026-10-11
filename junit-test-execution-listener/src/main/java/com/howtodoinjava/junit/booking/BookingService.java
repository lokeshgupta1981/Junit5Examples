package com.howtodoinjava.junit.booking;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BookingService {

    private final int nightlyRate;

    public BookingService(int nightlyRate) {
        this.nightlyRate = nightlyRate;
    }

    public int price(LocalDate checkIn, LocalDate checkOut) {
        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        if (nights <= 0) {
            throw new IllegalArgumentException("Check-out must be after check-in");
        }
        return Math.toIntExact(nights * nightlyRate);
    }
}

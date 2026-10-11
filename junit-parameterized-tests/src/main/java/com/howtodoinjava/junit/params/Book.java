package com.howtodoinjava.junit.params;

import java.time.LocalDate;

public record Book(String title, String author, LocalDate published) {

    public boolean isNewRelease(LocalDate today) {
        return !published.isBefore(today.minusMonths(3));
    }
}

package com.howtodoinjava.junit.repeated;

import java.util.concurrent.atomic.AtomicLong;

public class OrderNumberGenerator {

    private final AtomicLong counter = new AtomicLong();

    public long next() {
        return counter.incrementAndGet();
    }
}

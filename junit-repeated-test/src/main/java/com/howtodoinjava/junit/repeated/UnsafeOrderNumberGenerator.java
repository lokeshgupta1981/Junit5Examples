package com.howtodoinjava.junit.repeated;

public class UnsafeOrderNumberGenerator {

    private long counter;

    public long next() {
        return ++counter;          // not atomic: read, add, write
    }
}

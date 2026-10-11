package com.howtodoinjava.junit.order;

import java.util.HashMap;
import java.util.Map;

public class Library {

    public enum Status { AVAILABLE, LENT }

    private final Map<String, Status> books = new HashMap<>();

    public void register(String title) {
        books.put(title, Status.AVAILABLE);
    }

    public void lend(String title) {
        if (books.get(title) != Status.AVAILABLE) {
            throw new IllegalStateException(title + " is not available");
        }
        books.put(title, Status.LENT);
    }

    public void giveBack(String title) {
        books.put(title, Status.AVAILABLE);
    }

    public Status statusOf(String title) {
        return books.get(title);
    }
}

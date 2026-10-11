package com.howtodoinjava.junit.library;

import java.util.HashMap;
import java.util.Map;

public class LoanService {

    private final Map<String, String> loans = new HashMap<>();

    public void borrow(String isbn, String member) {
        if (loans.containsKey(isbn)) {
            throw new IllegalStateException("Book already on loan: " + isbn);
        }
        loans.put(isbn, member);
    }

    public void giveBack(String isbn) {
        loans.remove(isbn);
    }

    public boolean isOnLoan(String isbn) {
        return loans.containsKey(isbn);
    }

    public int openLoans() {
        return loans.size();
    }

    public int lateFee(int daysLate) {
        return Math.max(0, daysLate) * 25;
    }
}

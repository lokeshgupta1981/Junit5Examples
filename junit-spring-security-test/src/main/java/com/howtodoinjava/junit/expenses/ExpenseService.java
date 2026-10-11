package com.howtodoinjava.junit.expenses;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final List<Expense> expenses = List.of(
            new Expense(1, "lokesh", "Train ticket", 45),
            new Expense(2, "alex", "Hotel", 180));

    public List<Expense> findByOwner(String owner) {
        return expenses.stream().filter(e -> e.owner().equals(owner)).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public String approve(long id) {
        return "Expense " + id + " approved";
    }
}

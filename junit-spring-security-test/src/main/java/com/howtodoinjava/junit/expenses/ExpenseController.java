package com.howtodoinjava.junit.expenses;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/api/public/status")
    public String status() {
        return "UP";
    }

    @GetMapping("/api/expenses")
    public List<Expense> myExpenses(@AuthenticationPrincipal UserDetails user) {
        return expenseService.findByOwner(user.getUsername());
    }

    @PostMapping("/api/admin/expenses/{id}/approve")
    public String approve(@PathVariable long id) {
        return expenseService.approve(id);
    }
}

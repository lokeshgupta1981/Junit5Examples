package com.howtodoinjava.junit.expenses;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ExpenseServiceSecurityTest {

    @Autowired
    private ExpenseService expenseService;

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminCanApprove() {
        assertEquals("Expense 2 approved", expenseService.approve(2));
    }

    @Test
    @WithMockUser(roles = "USER")
    void userIsDenied() {
        assertThrows(AccessDeniedException.class, () -> expenseService.approve(2));
    }

    @Test
    void noAuthenticationIsRejected() {
        assertThrows(AuthenticationCredentialsNotFoundException.class, () -> expenseService.approve(2));
    }
}

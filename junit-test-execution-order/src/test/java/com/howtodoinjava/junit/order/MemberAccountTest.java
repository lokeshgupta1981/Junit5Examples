package com.howtodoinjava.junit.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MemberAccountTest {

    @BeforeEach
    void printName(TestInfo info) {
        System.out.println("MemberAccountTest > " + info.getTestClass().orElseThrow().getSimpleName()
                + " > " + info.getDisplayName());
    }

    @Nested
    @Order(1)
    class SignUp {

        @Test
        @Order(2)
        void sendsWelcomeMail() {
        }

        @Test
        @Order(1)
        void createsAccount() {
        }
    }

    @Nested
    @Order(2)
    class Borrowing {

        @Test
        @Order(1)
        void borrowsFirstBook() {
        }

        @Test
        @Order(2)
        void hitsLoanLimit() {
        }
    }

    @Nested
    @Order(3)
    @TestMethodOrder(MethodOrderer.Default.class)
    class Reports {

        @Test
        void listsOverdueBooks() {
        }

        @Test
        void listsActiveLoans() {
        }
    }
}

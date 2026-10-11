package com.howtodoinjava.junit.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoanWorkflowTest {

    private final Library library = new Library();

    @BeforeEach
    void printName(TestInfo info) {
        System.out.println("LoanWorkflowTest > " + info.getDisplayName());
    }

    @Test
    @Order(1)
    void registersBook() {
        library.register("Dune");
        assertEquals(Library.Status.AVAILABLE, library.statusOf("Dune"));
    }

    @Test
    @Order(2)
    void lendsBook() {
        library.lend("Dune");
        assertEquals(Library.Status.LENT, library.statusOf("Dune"));
    }

    @Test
    @Order(3)
    void returnsBook() {
        library.giveBack("Dune");
        assertEquals(Library.Status.AVAILABLE, library.statusOf("Dune"));
    }

    @Test
    void checksUnknownBook() {
        assertEquals(null, library.statusOf("Emma"));
    }
}

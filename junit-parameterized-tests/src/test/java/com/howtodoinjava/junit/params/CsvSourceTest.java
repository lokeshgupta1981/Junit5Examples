package com.howtodoinjava.junit.params;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CsvSourceTest {

    @BeforeEach
    void printName(TestInfo info) {
        System.out.println(info.getDisplayName());
    }

    @ParameterizedTest(name = "{0}")
    @CsvSource({
        "'Dune, Messiah', Frank Herbert, 1969",
        "Emma,           Jane Austen,   1815"
    })
    void readsQuotedValues(String title, String author, int year) {
        Book book = new Book(title, author, LocalDate.of(year, 1, 1));
        assertEquals(year, book.published().getYear());
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true, nullValues = "N/A", textBlock = """
        title,           author,        published
        Dune,            Frank Herbert, 1965-08-01
        # a book without a known author
        Beowulf,         N/A,           1000-01-01
        """)
    void readsTextBlock(String title, String author, LocalDate published) {
        Book book = new Book(title, author, published);
        if (title.equals("Beowulf")) {
            assertNull(book.author());
        } else {
            assertEquals("Frank Herbert", book.author());
        }
    }
}

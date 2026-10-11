package com.howtodoinjava.junit.params;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookTest {

    @BeforeEach
    void printName(TestInfo info) {
        System.out.println(info.getDisplayName());
    }

    @ParameterizedTest
    @ArgumentsSource(NewReleaseArgumentsProvider.class)
    void detectsNewReleases(Book book) {
        assertTrue(book.isNewRelease(NewReleaseArgumentsProvider.TODAY));
    }

    @ParameterizedTest
    @CsvSource({
        "Dune, Frank Herbert, 1965-08-01",
        "Emma, Jane Austen,   1815-12-23"
    })
    void readsWithAccessor(ArgumentsAccessor arguments) {
        Book book = new Book(arguments.getString(0), arguments.getString(1), arguments.get(2, LocalDate.class));
        assertFalse(book.isNewRelease(NewReleaseArgumentsProvider.TODAY));
    }

    @ParameterizedTest
    @CsvSource({
        "Dune, Frank Herbert, 1965-08-01",
        "Emma, Jane Austen,   1815-12-23"
    })
    void readsWithAggregator(@AggregateWith(BookAggregator.class) Book book) {
        assertEquals(4, book.title().length());
    }

    static class BookAggregator implements ArgumentsAggregator {

        @Override
        public Book aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) {
            return new Book(arguments.getString(0), arguments.getString(1), arguments.get(2, LocalDate.class));
        }
    }
}

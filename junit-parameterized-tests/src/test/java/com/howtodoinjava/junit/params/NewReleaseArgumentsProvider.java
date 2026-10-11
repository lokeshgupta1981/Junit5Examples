package com.howtodoinjava.junit.params;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.ParameterDeclarations;

public class NewReleaseArgumentsProvider implements ArgumentsProvider {

    static final LocalDate TODAY = LocalDate.of(2026, 10, 11);

    @Override
    public Stream<? extends Arguments> provideArguments(ParameterDeclarations parameters,
                                                        ExtensionContext context) {
        return Stream.of(
                Arguments.of(new Book("The Last Shift", "A. Rao", TODAY.minusDays(10))),
                Arguments.of(new Book("Night Train", "M. Weber", TODAY.minusMonths(2))));
    }
}

package com.howtodoinjava.junit.repeated;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RepetitionInfoTest {

    @BeforeEach
    void logRepetition(RepetitionInfo repetitionInfo) {
        System.out.println("Batch " + repetitionInfo.getCurrentRepetition()
                + " of " + repetitionInfo.getTotalRepetitions());
    }

    @RepeatedTest(3)
    void generatesUniqueNumbersForGrowingBatches(RepetitionInfo repetitionInfo) {
        int batchSize = repetitionInfo.getCurrentRepetition() * 100;
        OrderNumberGenerator generator = new OrderNumberGenerator();
        Set<Long> numbers = new HashSet<>();
        for (int i = 0; i < batchSize; i++) {
            numbers.add(generator.next());
        }
        assertEquals(batchSize, numbers.size());
    }
}

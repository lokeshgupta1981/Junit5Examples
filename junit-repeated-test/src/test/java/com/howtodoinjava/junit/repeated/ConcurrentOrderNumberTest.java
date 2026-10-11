package com.howtodoinjava.junit.repeated;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConcurrentOrderNumberTest {

    @RepeatedTest(20)
    void generatesUniqueNumbersUnderLoad() {
        OrderNumberGenerator generator = new OrderNumberGenerator();
        Set<Long> numbers = ConcurrentHashMap.newKeySet();
        try (ExecutorService executor = Executors.newFixedThreadPool(4)) {
            for (int task = 0; task < 4; task++) {
                executor.submit(() -> {
                    for (int i = 0; i < 10_000; i++) {
                        numbers.add(generator.next());
                    }
                });
            }
        }
        assertEquals(40_000, numbers.size());
    }
}

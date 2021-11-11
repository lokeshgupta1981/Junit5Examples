package com.howtodoinjava.junit5.examples.ordering;

import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
class OrderedTestClassesExample {

    @Nested
    @Order(1)
    class SetupTests {

        @Test
        void test1() {
        }
    }

    @Nested
    @Order(2)
    class AppFlowTests {

        @Test
        void test2() {
        }
    }
}

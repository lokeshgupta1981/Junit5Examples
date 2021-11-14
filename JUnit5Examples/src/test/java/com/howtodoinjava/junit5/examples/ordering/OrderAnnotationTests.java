package com.howtodoinjava.junit5.examples.ordering;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderAnnotationTests {
	@Order(5)
	@Test
	void testE() {
		assertTrue(true);
	}

	@Order(4)
	@Test
	void testA() {
		assertTrue(true);
	}

	@Order(3)
	@Test
	void testD() {
		assertTrue(true);
	}

	@Order(2)
	@Test
	void testC() {
		assertTrue(true);
	}

	@Order(1)
	@Test
	void testB() {
		assertTrue(true);
	}
}

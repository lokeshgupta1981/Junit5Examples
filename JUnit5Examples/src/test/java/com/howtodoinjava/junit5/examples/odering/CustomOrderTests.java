package com.howtodoinjava.junit5.examples.odering;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(DeprecatedInEndTestOrder.class)
public class CustomOrderTests {
	@Test
	@Deprecated
	void testC() {
		assertTrue(true);
	}

	@Test
	void testA() {
		assertTrue(true);
	}

	@Test
	void testD() {
		assertTrue(true);
	}
	@Deprecated
	@Test
	void testE() {
		assertTrue(true);
	}

	@Test
	void testB() {
		assertTrue(true);
	}
}

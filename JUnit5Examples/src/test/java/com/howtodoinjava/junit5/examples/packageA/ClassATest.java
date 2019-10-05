package com.howtodoinjava.junit5.examples.packageA;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ClassATest 
{
	@Tag("production")
	@Test
	@DisplayName("testCaseA inside ClassATest inside packageA")
	public void testCaseA() {
		
	}
}

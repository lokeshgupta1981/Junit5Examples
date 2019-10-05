package com.howtodoinjava.junit5.examples.packageB;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ClassBTest 
{
	@Tag("development")
	@Test
	@DisplayName("testCaseB inside ClassBTest inside packageB")
	public void testCaseB() {
		
	}
}

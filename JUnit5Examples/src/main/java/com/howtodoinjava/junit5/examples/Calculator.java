package com.howtodoinjava.junit5.examples;

public class Calculator {
	
	private Calculator() {}

	public static int add(int i, int j) {
		return i + j;
	}
	
	public static int subtract(int i, int j) {
		return i - j;
	}
	
	public static int multiply(int i, int j) {
		return i * j;
	}
	
	public static int divide(int i, int j) {
		return i / j;
	}
}

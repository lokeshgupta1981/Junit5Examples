package com.howtodoinjava.junit5.examples.ordering;

import java.util.Comparator; 

import org.junit.jupiter.api.MethodDescriptor;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.MethodOrdererContext;

public class DeprecatedInEndTestOrder implements MethodOrderer {

	private Comparator<MethodDescriptor> comparator = Comparator
				.comparing(md -> md.getMethod().isAnnotationPresent(Deprecated.class));

	@Override
	public void orderMethods(MethodOrdererContext context) {

		context.getMethodDescriptors().sort(comparator);
	}
}

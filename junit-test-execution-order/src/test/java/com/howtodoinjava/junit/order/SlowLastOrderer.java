package com.howtodoinjava.junit.order;

import java.util.Comparator;

import org.junit.jupiter.api.MethodDescriptor;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.MethodOrdererContext;
import org.junit.jupiter.api.Tag;

public class SlowLastOrderer implements MethodOrderer {

    @Override
    public void orderMethods(MethodOrdererContext context) {
        context.getMethodDescriptors().sort(
                Comparator.comparing(SlowLastOrderer::isSlow)
                        .thenComparing(MethodDescriptor::getDisplayName));
    }

    private static boolean isSlow(MethodDescriptor descriptor) {
        return descriptor.findRepeatableAnnotations(Tag.class).stream()
                .anyMatch(tag -> tag.value().equals("slow"));
    }
}

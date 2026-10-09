package com.howtodoinjava.junit6;

import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class CallCounterExtension implements BeforeEachCallback {

  private static final ExtensionContext.Namespace NAMESPACE =
      ExtensionContext.Namespace.create(CallCounterExtension.class);

  @Override
  public void beforeEach(ExtensionContext context) {
    ExtensionContext.Store store = context.getRoot().getStore(NAMESPACE);

    // JUnit 5: store.getOrComputeIfAbsent("calls", key -> new AtomicInteger(), AtomicInteger.class)
    AtomicInteger counter = store.computeIfAbsent("calls", key -> new AtomicInteger(), AtomicInteger.class);
    System.out.println("Test number " + counter.incrementAndGet());
  }
}

package com.howtodoinjava.junit.lifecycle;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.junit.jupiter.api.extension.TestInstancePreDestroyCallback;

/**
 * An extension that prints every lifecycle callback, so we can see where extension code runs around our methods.
 */
public class LifecycleLogger implements BeforeAllCallback, TestInstancePostProcessor, BeforeEachCallback,
    BeforeTestExecutionCallback, AfterTestExecutionCallback, AfterEachCallback, TestInstancePreDestroyCallback,
    AfterAllCallback {

  @Override
  public void beforeAll(ExtensionContext context) {
    System.out.println("[ext] BeforeAllCallback");
  }

  @Override
  public void postProcessTestInstance(Object testInstance, ExtensionContext context) {
    System.out.println("[ext]   TestInstancePostProcessor");
  }

  @Override
  public void beforeEach(ExtensionContext context) {
    System.out.println("[ext]   BeforeEachCallback");
  }

  @Override
  public void beforeTestExecution(ExtensionContext context) {
    System.out.println("[ext]   BeforeTestExecutionCallback");
  }

  @Override
  public void afterTestExecution(ExtensionContext context) {
    System.out.println("[ext]   AfterTestExecutionCallback");
  }

  @Override
  public void afterEach(ExtensionContext context) {
    System.out.println("[ext]   AfterEachCallback");
  }

  @Override
  public void preDestroyTestInstance(ExtensionContext context) {
    System.out.println("[ext]   TestInstancePreDestroyCallback");
  }

  @Override
  public void afterAll(ExtensionContext context) {
    System.out.println("[ext] AfterAllCallback");
  }
}

package com.howtodoinjava.junit.autoclose;

/**
 * A test resource that prints when JUnit closes it.
 */
class AuditLog implements AutoCloseable {

  private final String name;

  AuditLog(String name) {
    this.name = name;
  }

  @Override
  public void close() {
    System.out.println("close(): " + name);
  }
}

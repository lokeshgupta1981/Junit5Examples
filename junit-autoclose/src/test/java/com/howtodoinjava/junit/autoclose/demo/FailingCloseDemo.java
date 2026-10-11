package com.howtodoinjava.junit.autoclose.demo;

import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;

/**
 * Fails on purpose: close() throws, so JUnit reports the test as failed. The other field is still closed.
 * Run with: mvn test -Dtest=FailingCloseDemo
 */
class FailingCloseDemo {

  static class ReportFile implements AutoCloseable {
    @Override
    public void close() {
      System.out.println("ReportFile.close() throws");
      throw new IllegalStateException("Cannot flush report.csv");
    }
  }

  static class Connection implements AutoCloseable {
    @Override
    public void close() {
      System.out.println("Connection.close() still runs");
    }
  }

  @AutoClose
  Connection connection = new Connection();

  @AutoClose
  ReportFile report = new ReportFile();

  @Test
  void writesReport() {
    System.out.println("@Test writesReport passed");
  }
}

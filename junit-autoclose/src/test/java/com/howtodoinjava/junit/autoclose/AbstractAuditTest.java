package com.howtodoinjava.junit.autoclose;

import org.junit.jupiter.api.AutoClose;

/**
 * @AutoClose fields are inherited. Subclass fields close before superclass fields.
 */
abstract class AbstractAuditTest {

  @AutoClose
  AuditLog baseLog = new AuditLog("superclass field");
}

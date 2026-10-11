package com.howtodoinjava.junit.assumptions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.abort;

import java.util.Optional;
import org.junit.jupiter.api.Test;

/** abort(): stops the test at once; the generic return type lets us use it inside expressions. */
class AbortTest {

  @Test
  void callsPaymentSandbox() {
    String url = Optional.ofNullable(System.getenv("PAYMENT_SANDBOX_URL"))
        .orElseGet(() -> abort("PAYMENT_SANDBOX_URL is not set"));    // aborted when missing

    assertTrue(url.startsWith("https://"));
  }

  @Test
  void abortsForUnsupportedOs() {
    String os = System.getProperty("os.name");
    String shell = switch (os.split(" ")[0]) {
      case "Linux", "Mac" -> "/bin/sh";
      case "Windows" -> "cmd.exe";
      default -> abort("No shell known for " + os);
    };

    assertTrue(shell.endsWith("sh") || shell.endsWith(".exe"));
  }
}

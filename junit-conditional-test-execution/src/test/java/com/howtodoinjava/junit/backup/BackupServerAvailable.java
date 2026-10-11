package com.howtodoinjava.junit.backup;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

public class BackupServerAvailable implements ExecutionCondition {

  @Override
  public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
    String host = context.getConfigurationParameter("backup.server.host").orElse("localhost");
    try (Socket socket = new Socket()) {
      socket.connect(new InetSocketAddress(host, 9000), 200);
      return ConditionEvaluationResult.enabled("Backup server " + host + ":9000 is reachable");
    } catch (IOException e) {
      return ConditionEvaluationResult.disabled("Backup server " + host + ":9000 is not reachable");
    }
  }
}

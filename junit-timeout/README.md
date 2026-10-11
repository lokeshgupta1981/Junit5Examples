Source code for the article https://howtodoinjava.com/junit/timeout/

Versions: Java 25, JUnit 6.1.3 (junit-bom), Maven 3.9 or later, Maven Surefire 3.6.0. The Jupiter API used here is the same in JUnit 5.11+, so the tests also compile against JUnit 5.

## Run

```bash
mvn test                                   # 10 tests, all pass
mvn test -Dtest=TimeoutFailureDemo         # 4 timeouts on purpose (about 9 s)
mvn test -Dtest=TimeoutFailureDemo#busyLoopIgnoresInterrupt -Djunit.jupiter.execution.timeout.mode=disabled
mvn test -Dtest=TimeoutFailureDemo#slowGatewayTimesOut -Djunit.jupiter.execution.timeout.threaddump.enabled=true
```

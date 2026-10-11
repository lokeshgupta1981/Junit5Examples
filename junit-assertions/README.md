Source code for the article https://howtodoinjava.com/junit/assertions/

Versions: Java 25, JUnit 6.1.3 (junit-bom), Maven 3.9 or later, Maven Surefire 3.6.0. The Jupiter API used here is the same in JUnit 5.11+, so the tests also compile against JUnit 5.

## Run

```bash
mvn test                                   # 13 tests, all pass
mvn test -Dtest=AssertionsFailureDemo      # fails on purpose, shows the failure messages
```

Source code for the article https://howtodoinjava.com/junit/conditional-test-execution/

Versions: Java 25, JUnit 6.1.3 (junit-bom), Maven 3.9 or later, Maven Surefire 3.6.0. The Jupiter API used here is the same in JUnit 5.11+, so the tests also compile against JUnit 5.

## Run

```bash
mvn test                                   # 20 tests, 9 skipped on Linux x86_64 / Java 25 without CI
mvn test -Dtest=PropertyAndEnvConditionsTest -Dbackup.target=s3://nightly-backups
CI=true mvn test -Dtest=PropertyAndEnvConditionsTest
mvn test -Dfeature.incremental=true -Dtest=CustomConditionsTest
```

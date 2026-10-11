Source code for the article https://howtodoinjava.com/junit/assumptions/

Versions: Java 25, JUnit 6.1.3 (junit-bom), Maven 3.9 or later, Maven Surefire 3.6.0. The Jupiter API used here is the same in JUnit 5.11+, so the tests also compile against JUnit 5.

## Run

```bash
mvn test                                                              # 30 tests, 11 skipped
mvn test -Dtest=CiOnlyTest                                            # skipped on a laptop
CI=true mvn test -Dtest=CiOnlyTest                                    # runs
mvn test -Dtest=AssumptionVsAssertionDemoTest -DexcludedGroups=none   # 1 failed, 1 skipped (on purpose)
mvn test -Dtest=AssumeTrueTest -Dtest.db.url=jdbc:h2:mem:test         # all 3 run
```

AssumptionResultsTest expects a local Linux run on Java 25 without CI, test.db.url or PAYMENT_SANDBOX_URL set.

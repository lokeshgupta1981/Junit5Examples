Source code for the article https://howtodoinjava.com/junit/test-suites/

Versions: Java 25, JUnit 6.1.3 (junit-bom, junit-platform-suite), Maven Surefire 3.6.0, Gradle 9.8.1 (wrapper).

Run with Maven:

    mvn test                         # the 7 test classes matched by Surefire's default includes, no suites
    mvn test -Dtest=CheckoutSuite    # one suite
    mvn test -Psuites                # all *Suite classes except EmptySuite
    mvn test -Dtest=EmptySuite       # fails on purpose: a suite without selectors discovers no tests

Run with Gradle:

    ./gradlew test                   # Jupiter tests only, suite engine excluded
    ./gradlew suiteTest              # suites only

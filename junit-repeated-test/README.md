Source code for the article https://howtodoinjava.com/junit/repeated-test/

Versions: Java 25, JUnit 6.1.3 (junit-bom), Maven Surefire 3.6.0.

    mvn test                                # all repeated tests, all pass
    mvn test -Dtest=FlakyGatewayDemo        # fails on purpose: failureThreshold = 1 skips 7 of 10 repetitions
    mvn test -Dtest=UnsafeGeneratorDemo     # fails on purpose in some repetitions: race condition

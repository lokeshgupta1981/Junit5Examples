Source code for the article https://howtodoinjava.com/junit/parameterized-tests/

Versions: Java 25, JUnit 6.1.3 (junit-bom, junit-jupiter includes junit-jupiter-params), Maven Surefire 3.6.0.

Each test prints its display name from a @BeforeEach method.

    mvn test                                     # 54 test invocations, all pass
    mvn test -Dtest=ParameterizedMistakesDemo    # fails on purpose: strict argument count and zero arguments

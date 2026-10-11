Source code for the article https://howtodoinjava.com/junit/test-execution-order/

Versions: Java 25, JUnit 6.1.3 (junit-bom), Maven Surefire 3.6.0.

Each test prints its display name from a @BeforeEach method, so the console shows the order.

    mvn test
    mvn test -Dtest=RandomOrderTest                                              # seed 42 from junit-platform.properties
    mvn test -Dtest=RandomOrderTest -Djunit.jupiter.execution.order.random.seed=7
    mvn test -Dtest=DefaultOrderTest '-Djunit.jupiter.testmethod.order.default=org.junit.jupiter.api.MethodOrderer$MethodName'

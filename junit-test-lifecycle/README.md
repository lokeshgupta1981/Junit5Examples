Source code for the article https://howtodoinjava.com/junit/test-lifecycle/

# JUnit test lifecycle

Versions: Java 25, JUnit 6.1.3 (junit-bom), Maven Surefire 3.6.0. The lifecycle is the same in JUnit 5.

| Class | Shows |
|---|---|
| ShippingCostTest | constructor and the four lifecycle methods with the default PER_METHOD lifecycle |
| ExtensionOrderTest, LifecycleLogger | where extension callbacks run around the lifecycle methods |
| PerClassShippingTest | the same lifecycle with @TestInstance(PER_CLASS) |
| NestedLifecycleTest | order of outer and inner @BeforeEach / @AfterEach for a @Nested test |
| DisabledTestInstanceTest | a disabled test still creates the test instance |

## Run

```bash
mvn test
mvn test -Dtest=ExtensionOrderTest
```

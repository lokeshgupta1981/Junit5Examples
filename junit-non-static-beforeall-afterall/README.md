Source code for the article https://howtodoinjava.com/junit/non-static-beforeall-afterall/

# Non-static @BeforeAll and @AfterAll with @TestInstance(PER_CLASS)

Versions: Java 25, JUnit 6.1.3 (junit-bom), Maven Surefire 3.6.0. The same code runs on JUnit 5.

| Class | Shows |
|---|---|
| BookCatalogTest | PER_CLASS: one instance, non-static @BeforeAll and @AfterAll use instance fields |
| AuthorSearchTest | non-static @MethodSource factory method with PER_CLASS |
| BorrowingTest | reset mutable fields in @BeforeEach; non-static @BeforeAll in a @Nested class |
| demo.DefaultPerClassDemo | per_class as the project default from junit-platform.properties (profile per-class) |
| demo.SharedInstanceParallelDemo | PER_CLASS with concurrent execution shares one instance between threads |

## Run

```bash
mvn test
mvn clean test -Pper-class -Dtest=DefaultPerClassDemo   # run mvn clean before the next normal run
mvn test -Dtest=SharedInstanceParallelDemo -Djunit.jupiter.execution.parallel.enabled=true
```

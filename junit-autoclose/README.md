Source code for the article https://howtodoinjava.com/junit/junit-autoclose-examples/

# JUnit @AutoClose examples

Versions: Java 25, JUnit 6.1.3 (junit-bom), Maven Surefire 3.6.0. @AutoClose exists since JUnit 5.11.

| Class | Shows |
|---|---|
| OrderPricingTest | @AutoClose on an HttpClient, a static ExecutorService and @AutoClose("disconnect") |
| CloseTimingTest | instance fields close after each test, static fields after @AfterAll |
| PerClassCloseTimingTest | with PER_CLASS, instance fields close once after the static fields |
| AbstractAuditTest, RefundAuditTest | inherited fields; subclass fields close first |
| OrderFileTest | a Stream from Files.lines() assigned in @BeforeEach |
| demo.NullFieldDemo | a null field is skipped with a warning |
| demo.FailingCloseDemo | an exception in close() fails the test; other fields still close |
| demo.InvalidFieldDemo | a field type without close() fails the test |
| demo.PrimitiveFieldDemo | a primitive field fails the test |
| AutoCloseResultsTest | runs the demo classes with the JUnit Platform Test Kit |

## Run

```bash
mvn test                              # the demo package is excluded
mvn test -Dtest=CloseTimingTest
mvn test -Dtest=NullFieldDemo         # passes with a warning
mvn test -Dtest=FailingCloseDemo      # 1 error on purpose
mvn test -Dtest=InvalidFieldDemo      # 1 error on purpose
mvn test -Dtest=PrimitiveFieldDemo    # 1 error on purpose
```

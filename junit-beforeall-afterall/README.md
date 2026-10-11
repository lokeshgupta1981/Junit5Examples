Source code for the article https://howtodoinjava.com/junit/beforeall-afterall/

# JUnit @BeforeAll and @AfterAll examples

Versions: Java 25, JUnit 6.1.3 (junit-bom), Maven Surefire 3.6.0. The @BeforeAll and @AfterAll API is the same in JUnit 5.
The tests start a stub HTTP server with the JDK class com.sun.net.httpserver.HttpServer, so no extra dependency is needed.

| Class | Shows |
|---|---|
| RateClientTest | static @BeforeAll starts one server for all tests, static @AfterAll stops it |
| AbstractRateServerTest, EuroRateTest | superclass @BeforeAll runs first, superclass @AfterAll runs last, TestInfo parameter |
| NestedRatesTest | @BeforeAll and @AfterAll in a @Nested class (static, Java 16+) |
| demo.NonStaticAfterAllDemo | non-static @AfterAll without PER_CLASS is a discovery error |
| demo.FailingBeforeAllDemo | an exception in @BeforeAll skips all tests, @AfterAll still runs |
| demo.FailingAfterAllDemo | an exception in @AfterAll fails the class |
| BeforeAllResultsTest | runs the demo classes with the JUnit Platform Test Kit |

## Run

```bash
mvn test                                 # the demo package is excluded
mvn test -Dtest=RateClientTest           # prints the lifecycle order
mvn test -Dtest=NonStaticAfterAllDemo    # discovery error on purpose
mvn test -Dtest=FailingBeforeAllDemo     # class error on purpose
mvn test -Dtest=FailingAfterAllDemo      # class error on purpose
```

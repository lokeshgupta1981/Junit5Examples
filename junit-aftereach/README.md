Source code for the article https://howtodoinjava.com/junit/aftereach/

# JUnit @AfterEach examples

Versions: Java 25, JUnit 6.1.3 (junit-bom), Maven Surefire 3.6.0. The @AfterEach API is the same in JUnit 5.

| Class | Shows |
|---|---|
| ReportCacheTest | @AfterEach clears a static cache after every test |
| LocaleResetTest | @BeforeEach saves and @AfterEach restores the default locale |
| AbstractExportTest, InvoiceExportTest | subclass @AfterEach runs before superclass @AfterEach, TestInfo parameter |
| demo.NoCleanupDemo | without cleanup the second test fails |
| demo.FailingTestDemo | @AfterEach runs when the test fails |
| demo.FailingCleanupDemo | an exception in @AfterEach fails the test |
| demo.SkippedTestsDemo | aborted tests run @AfterEach, disabled tests do not |
| demo.StaticAfterEachDemo | a static @AfterEach is a discovery error |
| demo.BothFailDemo | test and @AfterEach both throw, cleanup exception is suppressed |
| AfterEachResultsTest | runs the demo classes with the JUnit Platform Test Kit |

## Run

```bash
mvn test                              # the demo package is excluded
mvn test -Dtest=NoCleanupDemo         # 1 failure on purpose
mvn test -Dtest=FailingTestDemo       # 1 failure on purpose
mvn test -Dtest=FailingCleanupDemo    # 1 error on purpose
mvn test -Dtest=SkippedTestsDemo      # 2 skipped
mvn test -Dtest=StaticAfterEachDemo   # discovery error on purpose
mvn test -Dtest=BothFailDemo          # 1 failure with a suppressed exception
```

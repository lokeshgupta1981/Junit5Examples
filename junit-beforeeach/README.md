Source code for the article https://howtodoinjava.com/junit/beforeeach/

# JUnit @BeforeEach examples


Versions: Java 25, JUnit 6.1.3 (the @BeforeEach API is the same as in JUnit 5), Maven Surefire 3.6.0.

| Class | Shows |
|---|---|
| PlaylistTest | @BeforeEach creates a fresh Playlist before every test |
| LifecycleOrderTest | execution order of @BeforeAll, constructor, @BeforeEach, @Test, @AfterEach, @AfterAll (PER_METHOD) |
| PerClassLifecycleTest | @TestInstance(PER_CLASS): one instance, non-static @BeforeAll/@AfterAll, reset state in @BeforeEach |
| RepeatedPlaylistTest | @BeforeEach before every @RepeatedTest repetition, with RepetitionInfo |
| BeforeEachInjectionTest | TestInfo and @TempDir Path injected into @BeforeEach |
| BasePlaylistTest, RockPlaylistTest | superclass @BeforeEach runs first, superclass @AfterEach runs last |
| OverridingSetupTest | overriding the superclass @BeforeEach replaces it |
| demo.FailingSetupDemo | exception in @BeforeEach fails the test, @AfterEach still runs |
| demo.StaticBeforeEachDemo | static @BeforeEach is a discovery error |
| demo.NonStaticBeforeAllDemo | non-static @BeforeAll without PER_CLASS is a discovery error |
| demo.PrivateBeforeEachDemo | private @BeforeEach runs with a warning |
| demo.RepetitionInfoMixDemo | RepetitionInfo in @BeforeEach fails for a plain @Test |
| LifecycleResultsTest | runs the demo classes with the JUnit Platform Test Kit and checks their results |

## Run

```bash
mvn test                                  # 18 tests pass; the demo package is excluded
mvn test -Dtest=LifecycleOrderTest        # prints the lifecycle order
mvn test -Dtest=FailingSetupDemo          # 1 error on purpose
mvn test -Dtest=StaticBeforeEachDemo      # discovery error on purpose
mvn test -Dtest=NonStaticBeforeAllDemo    # discovery error on purpose
mvn test -Dtest=PrivateBeforeEachDemo     # passes with a warning
mvn test -Dtest=RepetitionInfoMixDemo     # 1 error on purpose
```

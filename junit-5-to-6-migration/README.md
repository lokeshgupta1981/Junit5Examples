Source code for the article https://howtodoinjava.com/junit/junit-5-vs-junit-6/

A small project migrated from JUnit 5.14.4 to JUnit 6.1.3. Each test class uses a JUnit 5 API that JUnit 6 removed, deprecated or changed, with the JUnit 5 line kept as a comment.

Versions: Java 25, Maven 3.9, JUnit 6.1.3, Maven Surefire 3.6.0

Run:

    mvn test

Run the CSV test that passes on JUnit 5 and fails on JUnit 6:

    mvn test -Dgroups=fails-on-junit6 -DexcludedTags=none

Compare with JUnit 5 (BuiltInExtensionsTest needs JUnit 6.1, so remove it first):

    mvn test -Djunit.version=5.14.4

| Test class | JUnit 5 code | JUnit 6 change |
|---|---|---|
| MethodOrderTest | MethodOrderer.Alphanumeric | MethodOrderer.MethodName; @TestMethodOrder applies to @Nested classes |
| CsvFileSourceTest | @CsvFileSource(lineSeparator = "\n") | lineSeparator removed |
| CallCounterExtension | Store.getOrComputeIfAbsent() | Store.computeIfAbsent() |
| JreConditionTest | @EnabledForJreRange(min = JRE.JAVA_11) | JAVA_8 to JAVA_16 deprecated |
| DisplayNameTest | display name fruit=apple | display name fruit = "apple" |
| CsvStrictQuotesTest | 'apple'x accepted | text after a closing quote rejected |
| BuiltInExtensionsTest | JUnit Pioneer | @DefaultLocale, @DefaultTimeZone, @SetSystemProperty (JUnit 6.1) |

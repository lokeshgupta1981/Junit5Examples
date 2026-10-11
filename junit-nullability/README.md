Source code for the article https://howtodoinjava.com/junit/nullability/

JSpecify nullability with JUnit 6: a @NullMarked coupon service, tests that rely on the @Contract of assertNotNull(), and a JUnit extension that handles the @Nullable result of ExtensionContext.Store.remove(). NullAway checks the code at compile time.

Versions: Java 25, JUnit 6.1.3, JSpecify 1.0.1, Error Prone 2.50.0, NullAway 0.14.2, maven-compiler-plugin 3.16.0, maven-surefire-plugin 3.6.0, Maven 3.9 or later.
.mvn/jvm.config holds the --add-exports options Error Prone needs on JDK 17+.

Run:

    mvn test

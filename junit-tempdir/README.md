Source code for the article https://howtodoinjava.com/junit/tempdir/

Versions: Java 25, JUnit 6.1.3 (junit-bom), Maven Surefire 3.6.0.

    mvn test                                  # 7 tests, all pass; CleanupModeTest keeps its directory on purpose (NEVER)
    mvn test -Dtest=CleanupOnSuccessDemo      # fails on purpose: ON_SUCCESS keeps the export file of the failed test

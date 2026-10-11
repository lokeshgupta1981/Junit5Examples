Source code for the article https://howtodoinjava.com/junit/html-report/

Versions: Java 25, JUnit 6.1.3 (junit-bom), Maven Surefire and Surefire Report plugin 3.6.0, Maven Site plugin 3.22.0, Project Info Reports plugin 3.9.0.

Run the tests and build the HTML report at target/reports/surefire.html:

    mvn surefire-report:report

Build the full project site with the report at target/site/surefire.html:

    mvn site

The class demo/RefundRulesDemoTest fails on purpose and is excluded from the default build.
Include it to see a failure in the report:

    mvn surefire-report:report -Dtest='OrderTotalsTest,RefundRulesDemoTest'

# Validation scope

The local check on 11 October 2026 passed with JDK 17.0.19 and Maven 3.9.16:

```sh
mvn -B -ntp -DskipTests compile
```

Scope: **JVM source compilation only**. The GitHub workflow repeats this limited check on Linux with Temurin 17. Its status does not certify other legacy examples, database-backed runtime behavior, JSP rendering, GraalVM native compilation, or Kubernetes deployment.

The README and catalog relative links were checked against the source tree. Database configuration in the native-image demos uses environment placeholders; no connection to an old public cloud endpoint was attempted. Do not use exposed credentials from Git history.

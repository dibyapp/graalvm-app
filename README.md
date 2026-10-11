# GraalVM Native Image Maven Profile: Spring Boot, JPA, and MySQL

Inspect an explicit **Maven `native` profile** for a historical **Spring Boot 3.1.3 / Java 17** application. This project uses Spring Data JPA, MySQL, and a `GET /getRecords` endpoint. Its native profile configures the main class, binary name, `--no-fallback`, and `compile-no-fork` during packaging.

This is a build-configuration experiment. The current POM does not declare Spring Boot's Maven plugin, so the Spring AOT step needs explicit attention before a usable native executable can be claimed. It is not a current production template or a measured performance result.

## Source map

| File | What to inspect |
| --- | --- |
| [pom.xml](pom.xml) | Native profile and its package-phase execution |
| [GraalvmNativeAppApplication.java](src/main/java/com/neonci/GraalvmNativeAppApplication.java) | Configured main class |
| [GrallController.java](src/main/java/com/neonci/controller/GrallController.java) | Local endpoint |
| [TestUser.java](src/main/java/com/neonci/entity/TestUser.java) | `testuser` entity |
| [TestUserRepository.java](src/main/java/com/neonci/repository/TestUserRepository.java) | JPA repository |

## Inspect and compile the JVM source first

```sh
git clone https://github.com/dibyapp/graalvm-app.git
cd graalvm-app
mvn -DskipTests package
```

Use Java 17 and Maven. The default package goal produces a normal JAR; the POM does not establish a Spring Boot executable-JAR repackaging step. Do not assume `java -jar` or `mvn spring-boot:run` is ready without adding the appropriate Boot plugin configuration.

Prepare only a disposable local MySQL schema and supply the environment variables in [DATABASE.md](docs/DATABASE.md). The existing context-loading test requires database infrastructure. The entity ID uses `int` while the repository declares `Long`; align them before adding ID operations. The controller returns entities with a password property, so only synthetic records are appropriate; add a DTO before exposing any endpoint.

## What the native profile does

`mvn -Pnative -DskipTests package` activates a profile that binds `native:compile-no-fork` to packaging. Inspect the profile before running it: a successful native application also requires Spring AOT assets and a compatible GraalVM/C toolchain.

Compare this POM with the [Boot 3.1.3 native-image documentation](https://docs.spring.io/spring-boot/docs/3.1.3/reference/html/native-image.html) and the [sibling Boot-plugin demo](https://github.com/dibyapp/spring-boot-graal-demo). Add and verify the missing Boot/AOT lifecycle rather than treating a plugin declaration as proof of a working executable.

## Troubleshooting and next steps

- Missing AOT initialization: check whether the build actually ran Spring's AOT processing and packaged its generated assets.
- Missing database environment: set all three required variables before a runtime or context-loading test.
- Native build errors: match GraalVM and C compiler prerequisites to the historical Java 17 / Boot 3.1 toolchain.
- A valuable contribution is a minimal, reproducible native build with a local database fixture, followed by an actual `/getRecords` query using synthetic data.

No native executables, benchmark claims, or verified deployment instructions are promised by this README. For new work, use supported dependencies and test the migration independently.

## What has been checked?

The JVM source compilation only check passed locally. [Validation scope](docs/VALIDATION.md) · [CI](https://github.com/dibyapp/graalvm-app/actions/workflows/starter-check.yml). This is a limited check, not certification of the whole repository.

## Continue learning

| What you want to understand | Repository |
| --- | --- |
| IoC, dependency injection, bean lifecycle, AOP | [Spring Core examples](https://github.com/dibyapp/spring-core-all-concepts-tutorial) |
| Controllers, JSP forms, validation, CRUD, PRG | [Spring MVC examples](https://github.com/dibyapp/spring-mvc-all-concepts-tutorial) |
| Requests, sessions, cookies, dispatching, JDBC | [Java Servlet examples](https://github.com/dibyapp/java-servlets-all-concepts) |
| Spring Boot 3.1 JPA/MySQL native-image demo | [Spring Boot GraalVM demo](https://github.com/dibyapp/spring-boot-graal-demo) |
| Explicit native Maven profile experiment | [GraalVM application](https://github.com/dibyapp/graalvm-app) |
| Historical Boot 3.0 AOT/Kubernetes experiment | [Spring native study project](https://github.com/dibyapp/spring-native) |

## Contribute or report a problem

Found a broken example? Include its folder, Java/tool versions, a minimal reproduction, and a sanitized error. See [CONTRIBUTING.md](CONTRIBUTING.md). A small fix with a repeatable before/after result is useful; unverified performance claims are not.

Maintained by [dibyapp](https://github.com/dibyapp). [MIT](LICENSE) applies to the author's original contributions; inherited material and bundled third-party files retain their own terms. Read [THIRD_PARTY_NOTICES.md](THIRD_PARTY_NOTICES.md). If an example helped you understand a concept, a star or a link to that specific example helps other learners find it.

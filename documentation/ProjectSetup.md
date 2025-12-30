# Project Setup

## Generate project skeleton

Use [Spring Initializr](https://start.spring.io/index.html) to create sceleton

- Maven
- Java
- Spring Boot 4.0.1
- Artefact: flyway-demo
- JAR
- Java 17
- Dependencies
    - Spring Boot DevTools
    - H2 Database
    - Lombok
    - Flyway Migration
    - Spring Web
    - Validation
    - Spring Data JPA
    - Thymeleaf
    - ~~Docker Compose Support~~
    - ~~MariaDB Driver~~

To enable `swagger` / `OpenApi`, manually add to `./pom.xml`

```ini
<dependency>  
    <groupId>org.springdoc</groupId>  
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>  
    <version>2.5.0</version>  
</dependency>
```

## First implementation

Setup H2 data source and hibernate / JPA behavior in `src/main/resources/application.properties`

Adapt generic information for `swagger UI` in  `src/main/java/com/freezer/inventory/config/OpenApiConfig.java`

Create entity and data base creation

- Annotate class `FrozenItem` in `src/main/java/com/freezer/inventory/models/FrozenItem.java` with `@Entity` and `@Data`
- Create table and sequence in `src/main/resources/db/migration/V001__create_frozen_item.sql`.

Create repository, service and controller (`src/main/java/com/freezer/inventory``/repositories/FrozenItemRepository.java`, `/services/FrozenItemService.java`, `/controllers/FrozenItemController.java`)

Check possible error scenario of `FrozenItemController` and annotate it for documentation in `swagger UI`

***

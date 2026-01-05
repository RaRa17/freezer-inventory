# Use Spring Boot and Maven Profiles

## Preparation in Project Setup

Define the `maven`-profiles in the `pom.xml`-file.

According to the active `maven`-profile the property `SPRING_PROFILE` is set. In this example the `local_h2` profile is activated if not specified otherwise.

 ```xml
    <!-- !*! Define MAVEN profiles and set SPRING_PROFILE accordingly -->
    <profiles>
        <profile>
            <id>local_h2</id>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
            <properties>
                <SPRING_PROFILE>local_h2</SPRING_PROFILE>
            </properties>
        </profile>
        <profile>
            <id>net_mysql</id>
            <properties>
                <SPRING_PROFILE>net_mysql</SPRING_PROFILE>
            </properties>
        </profile>
    </profiles>
 ```

In the `application.properties` file the spring profile defined by the `maven`-property `SPRING_PROFILE` activated.

```
spring.profiles.active=@SPRING_PROFILE@
```

Create Dockerfile at project root folder specifically for each spring profile  (e.g., `Dockerfile.local_h2`)

```dockerfile
FROM openjdk:27-ea-slim  
WORKDIR /app  
COPY ./target/inventory-0.0.1-SNAPSHOT.jar /app/<app-name>.jar  
EXPOSE <exposed-port>  
CMD ["java", "-jar", "<app-name>.jar", "--spring.profiles.active=local_h2"]
```

## Provide database credentials

To configure the database access, the required credentials must be provided as environment variables.

The `application-<profile>.properties` imports a file `.env.<profile>` which contains the required settings and build the spring boot attribute based on that.

```dotenv
spring.config.import=optional:file:.env.net_mysql[.properties]

spring.datasource.url=jdbc:mysql://${MYSQL_HOST}:${MYSQL_PORT}/${MYSQL_DB}
spring.datasource.username = ${MYSQL_USER}
spring.datasource.password  = ${MYSQL_PASSWORD}
```

Create a `.env` file at the project root folder and add content like the following example.

```dotenv
# Secrets specific to profile `local_mysql`

## Database credentials
MYSQL_HOST=<ip-address-of-mysql-server>
MYSQL_PORT=<port-of-mysql-server>
MYSQL_DB=<database-name>
MYSQL_USER=<database-user>
MYSQL_PASSWORD=<database-password>
```

```dotenv
# Secrets specific to profile `local_h2`

## Database credentials
H2_USER=admin
H2_PASSWORD=mypassword
```

## Build and deploy

Build java archive

```powershell
.\mvnw.cmd clean package
.\mvnw.cmd package
```

> [!info]
> The jar file "contains" all spring profiles and the active one will be selected by the call option `spring.profiles.active`

Create one image per spring profile based on all on the same java archive and activated by the --spring.profiles.active in the Dockerfile

```bash
docker build -t rara69/freezer-inventory:local_h2.0.0.1    -f .\Dockerfile.local_h2 .
docker build -t rara69/freezer-inventory:local_mysql.0.0.1 -f .\Dockerfile.local_mysql .
```

Run each image on a different port (for testing purposes)

```bash
docker run --name ct_freezer_h2    -d -p 8090:8090 rara69/freezer-inventory:local_h2.0.0.1 
docker run --name ct_freezer_mysql -d -p 8091:8090 rara69/freezer-inventory:local_mysql.0.0.1 
```

To check the version and profile information of each container, the end point `GET /api/version` can be used. 

Publish images to docker hub

```bash
docker login -u <docker-hub-user> -p <docker-hub-pat>
```

```bash
docker push rara69/freezer-inventory:local_h2.0.0.1
docker push rara69/freezer-inventory:local_mysql.0.0.1
```

***

FROM openjdk:27-ea-slim
WORKDIR /app
COPY ./target/inventory-0.0.1-SNAPSHOT.jar /app/freezer.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=local_h2", "freezer.jar"]
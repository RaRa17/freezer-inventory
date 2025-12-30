# freezer-inventory

## Description

This is a sample application to bring together some technologies and best (?) practices.

Purpose is to give an overview of what is available in the freezer.

- Primary
    - What's in the freezer
    - When was it put there (how old is it)
    - Get warning on overdue stuff
    - Get proposal what to cook with it
- To be added later
    - Creation of QR-labels
    - Check in / check out based on QR

## Technologies used

- Spring Boot
- Flyway database migration
- Swagger documentation and access to Backend endpoints

- `ThymeLeaf` Server side created webpage for easier user interaction

based on [[REF Build Your Own News Reader From Scratch with Spring Boot]]

## Project Setup

Use [Spring Initializr](https://start.spring.io/index.html) to create sceleton

- Maven
- Java
- Spring Boot 4.0.1
- Artefact: flyway-demo
- JAR
- Java 17
- Dependencies
    - Spring Boot DevTools
    - Lombok
    - Docker Compose Support
    - Spring Web
    - Spring Data JPA
    - H2 Database
    - MariaDB Driver
    - Flyway Migration
    - Validation
    - Thymeleaf

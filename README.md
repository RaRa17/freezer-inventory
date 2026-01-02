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

- `Intellij IDEA`
- Spring Boot
- H2 database
- MySQL database
- PostgreSql database
- Flyway database migration
- Swagger documentation and access to Backend endpoints
- Still to be added:
  - `ThymeLeaf` Server side created webpage for easier user interaction

Based on [Build Your Own News Reader From Scratch with Spring Boot](https://www.youtube.com/watch?v=T6czePZZ0Vo)
by [Wazoo Web Bytes](https://www.youtube.com/@wazoowebbytes). THANKS A LOT for this tutorial!

## Project Setup

see `/documentation/ProjectSetup.md`

## How to use the application

see `/documentation/HowToUse.md`

## Key learnings

Start building the documentation and the application in parallel. E.g., document `ProjectSetup` along with doing it.

Writing Swagger documentation helps to analyze possible error cases and fault reactions.

File-based H2 (with its `h2-console`) is fast and quite handy for development.

## Still to improve

- Use `ThymeLeaf` for server generated homepage / front-end
- Create multiple profiles to use with local-H2, local-MySql or Network-MySql
- Implement build to docker

***


코드 복사
# Spring Boot CI/CD Demo Project

This project is a simple Spring Boot application designed to demonstrate CI/CD capabilities. It provides a RESTful API for managing members, using **Java 17**, **Spring Boot 3.3**, **Gradle**, and an **H2 in-memory database**.

## Table of Contents

- [Introduction](#introduction)
- [Project Structure](#project-structure)
- [Development Guide](#development-guide)
    - [1. Project Configuration](#1-project-configuration)
    - [2. Managing Dependencies](#2-managing-dependencies)
    - [3. Writing Code](#3-writing-code)
    - [4. Switching to Another Database](#4-switching-to-another-database)

## Introduction

This project serves as a starting point for developing Spring Boot applications with integrated CI/CD pipelines. It demonstrates how to set up a basic RESTful API, manage dependencies, and structure your codebase for efficient development.

## Project Structure

```bash
project/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── helloworld/
│   │   │               ├── SpringRestfulApiApplication.java
│   │   │               ├── Member.java
│   │   │               ├── MemberController.java
│   │   │               └── MemberRepository.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── helloworld/
│                       └── SpringRestfulApiApplicationTests.java
├── build.gradle
├── settings.gradle
├── Dockerfile
├── .dockerignore
└── README.md
```
## Development Guide

### 1. Project Configuration

- **Package Structure**

  The main package for the application is `com.example.helloworld`. All your Java classes should reside under this package or its sub-packages.

- **Application Entry Point**

  The main class is `SpringRestfulApiApplication.java`, located at `src/main/java/com/example/helloworld/`.

- **Application Properties**

  Configuration properties are located in `src/main/resources/application.properties`. Here you can configure settings like server port, database connections, and other application-specific properties.

### 2. Managing Dependencies

Dependencies are managed in the `build.gradle` file located in the root directory of the project.

- **Adding a Dependency**

  To add a new dependency, open `build.gradle` and add your dependency under the `dependencies` section. For example:

  ```groovy
  dependencies {
      implementation 'org.springframework.boot:spring-boot-starter-web'
      implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
      runtimeOnly 'com.h2database:h2'
      // Add your dependency here
      implementation 'org.springframework.boot:spring-boot-starter-security'
  }
Updating Dependencies

To update existing dependencies, modify the version numbers in the build.gradle file or update the plugin versions as needed.

### 3. Writing Code
- **Entities**

Entity classes representing your database tables should be placed under `src/main/java/com/example/helloworld/.`

Example: `Member.java`

- **Controllers**

Controllers handling HTTP requests should also be placed under the same package.

Example: `MemberController.java`

- **Repositories**

Repository interfaces extending Spring Data JPA repositories should be placed under the same package.

Example: `MemberRepository.java`

- **Service Layer**

If you need to add business logic, you can create a service package:

```bash 
src/main/java/com/example/helloworld/service/
```
And add your service classes there.

### 4. Switching to Another Database

By default, the project uses an H2 in-memory database for simplicity and ease of setup. However, you can switch to another database (e.g., MySQL, PostgreSQL) by following these steps:

#### Step 1: Add the Database Dependency

In your `build.gradle` file, add the JDBC driver for your chosen database. Remove the H2 dependency if it's no longer needed.

- **For MySQL:**
```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    runtimeOnly 'mysql:mysql-connector-java'
    // Remove or comment out H2 dependency
    // runtimeOnly 'com.h2database:h2'
}
```
- **For PostgreSQL:**
```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    runtimeOnly 'org.postgresql:postgresql'
    // Remove or comment out H2 dependency
    // runtimeOnly 'com.h2database:h2'
}
```
#### Step 2: Update Application Properties
Modify `src/main/resources/application.properties` to configure your database connection.

- **For MySQL:**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Hibernate configurations
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```

- **For PostgreSQL:**
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA Hibernate configurations
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

#### Step 3: Ensure Database Availability
Make sure that the database server is running and the specified database (`your_database_name`) exists. You may need to create the database manually if it doesn't exist.

#### Step 4: Test the Connection
Run the application and check if it connects to the new database without errors. If you encounter any issues, verify your configurations and credentials.

#### Optional: Configure Connection Pooling
For production environments, it's recommended to configure a connection pool. Spring Boot uses HikariCP by default.

```properties
# Example HikariCP settings
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
```
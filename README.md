# A thymeleaf employee tracker crud application for a company.

## Project Description

This Spring Boot application demonstrates a basic CRUD (Create, Read, Update, Delete) operation using Thymeleaf as the templating engine. It provides a user interface for managing employee data, including functionalities for adding, viewing, updating and deleting employee records. The application also incorporates Spring Security for authentication and authorization defining different roles with varying access levels.

## Key Features

-   **Employee Management:** Allows users to perform CRUD operations on employee data.
-   **Thymeleaf Templating:** Utilizes Thymeleaf to render dynamic HTML views.
-   **Spring Data JPA:** Leverages Spring Data JPA for database interactions.
-   **Spring Security:** Implements authentication and authorization using Spring Security.
-   **Role-Based Access Control:** Defines roles such as EMPLOYEE, MANAGER, and ADMIN with different privileges.
-   **Data Validation:** Implements data validation for employee attributes.

## Table of Contents

*   [Installation](#installation)
*   [Setup](#setup)
*   [Running the Project](#running-the-project)
*   [Dependencies](#dependencies)

## Installation

1.  **Clone the repository:**

    ```bash
    git clone https://github.com/panoschron97/Thymeleaf_Crud_App.git
    cd Thymeleaf_Crud_App
    ```

## Setup

1.  **Database Configuration:**

    *   The application uses MySQL as the database. Ensure that you have MySQL installed and running.
    *   Create a database named `application`.
    *   Update the database connection properties in `src/main/resources/application.properties`:

        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/application
        spring.datasource.username=your_mysql_username
        spring.datasource.password=your_mysql_password
        ```

2.  **Security Configuration:**

    *   Create users and assign roles in your database using the security.sql to properly configure user roles. This needs to be done manually before the application is run, this may be subject to change in future commits.

        ```sql
        CREATE DATABASE IF NOT EXISTS application;
        --
        USE application;
        --
        CREATE TABLE IF NOT EXISTS users
        (
            username VARCHAR(100) NOT NULL,
            password VARCHAR(100) NOT NULL,
            enabled TINYINT NOT NULL,
            PRIMARY KEY (username ASC)
        );
        --
        INSERT INTO users
        VALUES
        ('alexis','{bcrypt}$2a$10$w24KzpXoY.HideqwQn7.r.1d1d9mStFeLTcd8Ae2RMFWwIqzIRv12',1),-- test
        ('nikos','{bcrypt}$2a$10$w24KzpXoY.HideqwQn7.r.1d1d9mStFeLTcd8Ae2RMFWwIqzIRv12',1),-- test
        ('panos','{bcrypt}$2a$10$w24KzpXoY.HideqwQn7.r.1d1d9mStFeLTcd8Ae2RMFWwIqzIRv12',1);-- test
        COMMIT;
        -- https://www.bcryptcalculator.com/
        CREATE TABLE IF NOT EXISTS authorities
        (
            username VARCHAR(100) NOT NULL,
            authority VARCHAR(100) NOT NULL,
            CONSTRAINT username_authority_UNQ UNIQUE(username ASC, authority ASC),
            CONSTRAINT username_FK FOREIGN KEY (username) REFERENCES users (username) ON DELETE CASCADE ON UPDATE CASCADE
        );
        --
        INSERT INTO authorities
        VALUES
        ('alexis','ROLE_EMPLOYEE'),
        ('nikos','ROLE_EMPLOYEE'),
        ('nikos','ROLE_MANAGER'),
        ('panos','ROLE_EMPLOYEE'),
        ('panos','ROLE_MANAGER'),
        ('panos','ROLE_ADMIN');
        COMMIT;
        --
        SELECT * FROM users;
        SELECT * FROM authorities;
        ```

## Running the Project

1.  **Build the project using Maven:**

    ```bash
    ./mvnw clean install
    ```

2.  **Run the application:**

    ```bash
    ./mvnw spring-boot:run
    ```

3.  **Access the application:**

    *   Open your web browser and navigate to `http://localhost:8080/employees/list`.
    *   Log in using the credentials configured in the `application.properties` or the SQL file mentioned above.

## Dependencies

*   **Spring Boot Starter Data JPA:** For database access using JPA.
*   **Spring Boot Starter Security:** For authentication and authorization.
*   **Spring Boot Starter Thymeleaf:** For using Thymeleaf as the templating engine.
*   **Spring Boot Starter Validation:** For implementing data validation.
*   **Spring Boot Starter Web MVC:** For building web applications with Spring MVC.
*   **Thymeleaf Extras Spring Security:** For integrating Spring Security with Thymeleaf.
*   **Spring Boot DevTools:** For development-time support, such as automatic restarts.
*   **MySQL Connector/J:** For connecting to MySQL databases.

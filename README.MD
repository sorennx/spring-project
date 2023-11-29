# Spring Boot 3.0 Security with JWT Implementation
This project showcases the implementation of security using Spring Boot 3.0 along with JSON Web Tokens (JWT). It
incorporates various features to ensure a robust authentication and authorization system.

# Features
* User Registration and Login: Enables users to register and log in with JWT authentication.
* Password Encryption: Implements BCrypt for secure password encryption.
* Role-Based Authorization: Utilizes Spring Security for role-based access control.
* Customized Access Denied Handling: Provides a customized mechanism for handling access denied situations.
* Logout Mechanism: Implements a secure logout mechanism.
* Refresh Token: Supports the use of refresh tokens for extended sessions.

# Technologies
* Spring Boot 3.0: The core framework for building the application.
* Spring Security: Ensures comprehensive security features for the application.
* JSON Web Tokens (JWT): Used for secure authentication and authorization.
* BCrypt: Employed for encrypting and securing passwords.
* Maven: Manages project build and dependencies.

# Getting Started
Ensure that the following software is installed on your local machine before proceeding:

JDK 17+
Maven 3+
Follow these steps to build and run the project:

* Run docker-compose up to initialize the necessary containers.
* Add a database named "jwt_security" to the PostgreSQL instance.
* Build the project: mvn clean install.
* Run the project: mvn spring-boot:run.
* The application will be accessible at http://localhost:8080.
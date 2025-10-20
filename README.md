# Self-Improvement Ranking System

A backend application built with Spring Boot that gamifies personal development. Users can create tasks, earn experience points (XP) upon completion, level up their stats, and achieve higher ranks.

## Description

This project provides a backend API for a self-improvement application. It allows users to register, log in, and manage tasks related to different personal stats like "Intelligence" and "Fitness". Completing tasks rewards the user with XP, which contributes to increasing their level in that specific stat. The user's overall level, determined by their lowest stat level, dictates their rank within the system, creating a balanced progression journey.

Authentication is handled securely using JWT (JSON Web Tokens), ensuring that user data and actions are protected.

## Features

-   **User Management**: User registration and secure login.
-   **JWT Authentication**: Secure, token-based authentication for all protected endpoints.
-   **Task Management**: Create, view, and delete tasks for specific users.
-   **Stat Tracking**: Tasks are tied to specific stats (e.g., `INTELLIGENCE`, `FITNESS`).
-   **XP and Leveling System**: Gain XP for completing tasks to level up individual stats. 100 XP is required to gain a level.
-   **Ranking System**: A tiered ranking system (E, D, C, B, A, S) based on the user's overall level.
-   **Stat Display**: View detailed user stats including rank, level, XP progress, and stat-specific levels.

## Getting Started

### Prerequisites

-   Java 17 or later
-   Maven
-   A running MySQL database instance

### Installation and Setup

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/manasarabdullahi/self-improvement-ranking-system.git
    cd self-improvement-ranking-system
    ```

2.  **Configure the Application**

    Open the `src/main/resources/application.properties` file and update the database details and JWT secret key.

    ```properties

    # Database Configuration
    spring.datasource.url=jdbc:mysql://localhost:3306/your_database
    spring.datasource.username=your_mysql_username
    spring.datasource.password=your_mysql_password

    # JPA Configuration
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true

    # JWT Secret Key Configuration
    jwt.secretKey=your-secret-jwt-key
    ```


3.  **Run the application:**
    ```bash
    ./mvnw spring-boot:run
    ```
    The application will start on `http://localhost:8080`.

## API Endpoints

All endpoints except for `/users/register` and `/users/login` require a `Bearer` token in the `Authorization` header.

### User Endpoints

| Method | Endpoint                    | Description                                         |
| :----- | :-------------------------- | :-------------------------------------------------- |
| `POST` | `/users/register`           | Register a new user.                                |
| `POST` | `/users/login`              | Authenticate a user and receive a JWT.              |
| `GET`  | `/users`                    | Get a list of all users.                            |
| `GET`  | `/users/{username}`         | Get details for a specific user.                    |
| `GET`  | `/users/{username}/stats`   | Get the rank, level, and XP stats for a user.       |
| `GET`  | `/users/{userId}`           | Get a user by their ID.                             |

### Task Endpoints

| Method   | Endpoint                           | Description                                            |
| :------- | :--------------------------------- | :----------------------------------------------------- |
| `POST`   | `/tasks/{userId}/create`           | Create a new task for the specified user.              |
| `GET`    | `/tasks/{userId}`                  | Retrieve all tasks for a specific user.                |
| `PUT`    | `/tasks/{username}/{taskId}/complete` | Mark a task as complete and update the user's stats.   |
| `DELETE` | `/tasks/{taskId}`                  | Delete a specific task by its ID.                      |

### Request Body Examples

**POST `/users/register` or `/users/login`**
```json
{
    "username": "newuser",
    "password": "password123"
}
```

**POST `/tasks/{userId}/create`**
```json
{
    "title": "Read a chapter of a book",
    "stat": "INTELLIGENCE",
    "xpReward": 25
}

3. Run the application:

bash
mvn spring-boot:run

4. Use the backend via Postman. JWT tokens are required for secured actions.

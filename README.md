# Quiz App REST API

This is a REST API for the backend of a quiz application, built with Java and Spring Boot, adhering to clean architecture principles and RESTful conventions.

The system supports two user types (`USER` and `ADMIN`) with clear access control, a robust JWT-based authentication system, and full CRUD functionality for content management.

## ✨ Key Features

* **Authentication & Authorization:** 🛡️ A secure system based on JWT (Access + Refresh tokens). The refresh token is stored in a protected `HttpOnly` cookie.
* **Role-Based Access Control:** 🧑‍🤝‍🧑 Clear separation of permissions between regular users (`ROLE_USER`) and administrators (`ROLE_ADMIN`), with each user assigned a single role.
* **Profile Management:** 👤 Users can view and update their profiles, upload avatars, and change their passwords.
* **Admin Dashboard:** 🛠️ Full CRUD functionality for administrators to manage categories and quizzes.
* **Quiz Taking:** 📝 Users can fetch lists of quizzes, take them, and receive detailed results.
* **Submission History:** 📜 Users can view the history of all their previous attempts in their profile.
* **Pagination & Filtering:** 🗂️ All lists (quizzes, history) support pagination, sorting, and filtering.
* **Custom Exception Handling:** ⛔ Centralized exception handling via `@ControllerAdvice` to provide clear and structured error responses.
* **Data Validation:** ✍️ Robust validation of all incoming data at the DTO level.

-----

## 🏗️ Architecture

The project follows the classic **Layered Architecture** for a clear separation of concerns:

**`Controller` → `Service` → `Repository` → `Entity`**

* **Controller Layer:** Handles HTTP requests, validates DTOs, and passes data to the service layer.
* **Service Layer:** Contains all business logic (creation, answer checking, access control).
* **Repository Layer:** Manages database interactions using Spring Data JPA.
* **DTOs (Data Transfer Objects):** Used to define a clear and secure API contract, separating the internal data model (`Entity`) from the external representation.

-----

## 🛠️ Tech Stack

| Category | Technology                 |
| :--- |:---------------------------|
| **Language** | Java 17                    |
| **Framework** | Spring Boot 3.x            |
| **Security** | Spring Security 6, JWT     |
| **Data Access**| Spring Data JPA, Hibernate |
| **Database** | MySQL                      |
| **Build Tool** | Maven                      |
| **Libraries**| Lombok, Apache Tika        |

-----

## 🔌 API Endpoints

### Authentication

| HTTP Method | Endpoint Path | Description |
| :--- | :--- | :--- |
| `POST` | `/api/v1/auth/register` | Register a new user. |
| `POST` | `/api/v1/auth/login` | Log in to the system, receive JWT tokens. |
| `POST` | `/api/v1/auth/refresh-token` | Refresh the Access token using the Refresh token from the cookie. |

### 🧑‍🎓 User API (`ROLE_USER`)

#### Profile & History

| HTTP Method | Endpoint Path | Description |
| :--- | :--- | :--- |
| `GET` | `/api/v1/profile` | Get the current user's profile data. |
| `PATCH` | `/api/v1/profile/update-profile` | Update the user's profile information. |
| `POST` | `/api/v1/profile/upload-image` | Upload or replace the user's profile avatar. |
| `GET` | `/api/v1/profile/image` | Get the user's profile avatar image data. |
| `DELETE` | `/api/v1/profile/delete-image` | Delete the user's profile avatar. |
| `POST` | `/api/v1/profile/change-password`| Change the user's password. |
| `GET` | `/api/v1/profile/quiz-history` | Get the user's paginated quiz submission history. |

#### Categories & Quizzes

| HTTP Method | Endpoint Path | Description |
| :--- | :--- | :--- |
| `GET` | `/api/v1/categories` | Get a list of all available categories. |
| `GET` | `/api/v1/quizzes` | Get a paginated and filterable list of quizzes. |
| `GET` | `/api/v1/quizzes/{quizId}` | Get a specific quiz for submission (without correct answers). |
| `POST` | `/api/v1/quizzes/{quizId}/submit` | Submit answers and get the results. |

### 👨‍💼 Admin API (`ROLE_ADMIN`)

#### Category Management

| HTTP Method | Endpoint Path | Description |
| :--- | :--- | :--- |
| `POST` | `/api/v1/categories` | Create a new category. |
| `GET` | `/api/v1/categories/{id}` | Get a single category by its ID. |
| `PATCH` | `/api/v1/categories/{id}`| Update a category's name. |
| `DELETE` | `/api/v1/categories/{id}`| Delete a category. |

#### Quiz Management

| HTTP Method | Endpoint Path | Description |
| :--- | :--- | :--- |
| `POST` | `/api/v1/management/quizzes` | Create a new quiz. |
| `GET` | `/api/v1/management/quizzes` | Get a paginated list of all quizzes for management. |
| `GET` | `/api/v1/management/quizzes/{quizId}` | Get full quiz details (including correct answers). |
| `PUT` | `/api/v1/management/quizzes/{quizId}` | Update a quiz (full replacement). |
| `DELETE` | `/api/v1/management/quizzes/{quizId}` | Delete a quiz. |

-----

## 🚀 How to Run

### Prerequisites

* Java 17 (or newer)
* Maven 3.6+
* A running instance of MySQL

### Setup and Launch

1.  **Clone the repository:**

    ```bash
    git clone https://github.com/your-username/quiz-app-rest-api.git
    cd quiz-app-rest-api
    ```

2.  **Configure Environment Variables:**
    This project loads configuration from a `.env` file in the project root.

    1.  Create a file named `.env` in the root directory.
    2.  Use the `.env.example` file as a template and provide the necessary values for your database connection and JWT secrets.
        ```dotenv
        # Database Configuration
        DB_SCHEMA=your_db_name
        DB_USERNAME=your_db_user
        DB_PASSWORD=your_db_password

        # JWT Configuration
        SECRET_KEY=your_super_secret_jwt_key
        ACCESS_TOKEN=86400000 # 1 day in milliseconds
        REFRESH_TOKEN=604800000 # 7 days in milliseconds
        ```

    The application will automatically import these variables on startup.

3.  **Run the application:**

    ```bash
    mvn spring-boot:run
    ```

    The application will be available at `http://localhost:8080`.

### 💾 Database Initialization

On the first run with an empty database, the `DataInitializer` class (`CommandLineRunner`) will automatically seed the tables with initial data:

* **Roles:** `ROLE_USER` and `ROLE_ADMIN`.
* A set of **initial categories**.
* An **administrator user** with the following credentials:
    * **Email:** `admin1@gmail.com`
    * **Password:** `admin1`
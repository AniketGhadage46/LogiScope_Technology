# E-commerce-Website
Assessment from LogiScope Technology pvt.ltd (User Managment API)

This is a Spring Boot application that provides RESTful APIs to manage user data for an e-commerce website. The application includes basic CRUD operations for users and additional features like password expiry management, bulk user creation, and more.

## Features

- **CRUD Operations**: Create, Retrieve, Update, and Delete users.
- **Password Expiry**: Check whether a user's password has expired.
- **Bulk User Creation**: Allows the creation of multiple users at once.
- **User Validation**: Ensure that a user exists before performing operations.

## APIs

### 1. POST `/users` – Create a New User

- **Request Body**:
  ```json
  {
    "firstName": "Abhijit",
    "lastName": "Debadwar",
    "email": "abhijit@gmail.com",
    "password": "password123"
  }

  Response:

{
  "id": 1,
  "firstName": "Abhijit",
  "lastName": "Debadwar",
  "email": "abhijit@gmail.com",
  "password": "password123"
  "passwordExpiryDate": "2025-05-01T00:00:00.000+00:00"
}

### 2. POST /users/get – Retrieve a User by Email
Request Body:

{
   "email": "abhijit@gmail.com"
}

Response:
{
  "id": 1,
  "firstName": "Abhijit",
  "lastName": "Debadwar",
  "email": "abhijit@gmail.com",
  "password": "password123"
  "passwordExpiryDate": "2025-05-01T00:00:00.000+00:00"
}

###3. PUT /users – Update a User by Email
Request Body:
{
  "id": 1,
  "firstName": "Abhijit",
  "lastName": "Debadwar",
  "email": "abhijit@gmail.com",
  "password": "password123"
}

Response:
{
  "id": 1,
  "firstName": "Abhijit",
  "lastName": "Debadwar",
  "email": "abhijit@gmail.com",
  "passwordExpiryDate": "2025-05-01T00:00:00.000+00:00"
}

###4.  DELETE /users – Delete a User by Email
Request Body:
{
  "email": "abhijit@gmail.com"
} 
Response: User Deleted Successfully

**Additional APIs**

1. Password Expiry Check
Endpoint: GET /users/password-expiry
Description: This API checks whether a user's password has expired.
Input: User email.
Example Request:
{
  "email": "abhijit@gmail.com"
}
Response: Returns true if password has expired, otherwise false.
{
  "isPasswordExpired": true
}

2. Bulk User Creation
Endpoint: POST /users/bulk
Description: This API allows the creation of multiple users in a single request.
Input: An array of user objects.
Example Request:
[
  {
  "firstName": "Abhishek",
  "lastName": "jadhav",
  "email": "abhishek@gmail.com",
  "password": "password1234"
  },
  {
  "firstName": "Vaibhav",
  "lastName": "pawar",
  "email": "vaibhav@gmail.com",
  "password": "password12345"
  }
]
Response: Returns an array of created users.
[
  {
    "id": 2,
    "firstName": "Abhishek",
    "lastName": "jadhav",
    "email": "abhishek@gmail.com",
    "passwordExpiryDate": "2025-05-01T00:00:00.000+00:00"
  },
  {
    "id": 3,
    "firstName": "Vaibhav",
    "lastName": "pawar",
    "email": "vaibhav@gmail.com",
    "passwordExpiryDate": "2025-05-01T00:00:00.000+00:00"
  }
]

## Technologies Used

- **Language**: Java 17 or higher
- **Backend Framework**: Spring Boot
- **Database**: MySQL8
- **Build Tool**: Maven
- **IDE**: Spring-tool-Suit

- ### Prerequisites

- Install MySQL 8.0 or higher.
- Create a new database named `ecommerce`.
- Make sure the database has the following table structure:

```sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    password_expiry_date DATETIME
);

****Application.properties file****
spring.application.name=LogiScope_Assessment

spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect


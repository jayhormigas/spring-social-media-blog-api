# Spring Social Media Blog API

## Project Description  
This social media application will be an api backend without a frontend. This may sound familiar, as this was the goal of your previous capstone project. Here the app will have the  same functionality, including the ability to manage user accounts and messages that they submit to the application. However, this application should be created using the Spring Framework, including Spring Boot, and Spring Web.

Similar to the previous iteration of this project, the backend you will create needs to be able to deliver the data needed to display this information as well as process actions like logins, registrations, message creations, message updates, and message deletions.

## Technologies Used  
- **Spring Boot** - Simplifies Spring setup with auto-configuration and embedded servers
- **Spring Data** - Streamlines database access with JPA and repository support   
- **Spring Framework** - Core framework for dependency injection, MVC, and transaction management

## Features  

### User Management  
- Register new users with unique usernames and secure passwords  
- User authentication with password validation  

### Message Handling  
- Users can create, edit, and delete messages  
- Retrieve all messages or filter by a specific user  
- Enforce text length limits (≤ 255 characters)  

### Data Persistence  
- **Spring Data JPA with H2 Database** for efficient SQL operations  
- **Repositories** for managing entities with CRUD operations   

### Security & Error Handling  
- Input validation for usernames, passwords, and message lengths  
- Error handling for duplicate users, invalid login attempts, and missing data  

---

## Getting Started  

### Clone the Repository  
```
sh
git clone https://github.com/jayhormigas/spring-social-media-blog-api.git  
cd spring-social-media-blog-api
```

### Setup & Run the Application
For Windows (CMD/PowerShell)
```
mvn clean install  
java -jar target/spring-social-media-api.jar  
```

For macOS/Linux (Terminal)
```
./mvnw clean install  
java -jar target/spring-social-media-api.jar  
```

### Run the API Locally
Once the application starts, you can use Postman or cURL to test the API:

Register a User
```
curl -X POST http://localhost:8080/register -H "Content-Type: application/json" -d '{"username": "testUser", "password": "password123"}'
```

Login a User
```
curl -X POST http://localhost:8080/login -H "Content-Type: application/json" -d '{"username": "testUser", "password": "password123"}'
```

Create a Message
```
curl -X POST http://localhost:8080/messages -H "Content-Type: application/json" -d '{"postedBy": 1, "messageText": "Hello, world!", "timePostedEpoch": 1710000000}'
```

Retrieve All Messages
```
curl -X GET http://localhost:8080/messages
```

## Usage
This API is designed for full-stack development, where a frontend (e.g., React, Angular) can consume its endpoints. It allows user authentication, message posting, and data retrieval, making it a solid foundation for a social media or messaging application.

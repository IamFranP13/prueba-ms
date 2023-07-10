# Technical Test Springboot Application

This project is a part of a technical test aiming to demonstrate the development of REST APIs using the Spring framework and Java. The application features two primary APIs - File upload API and User API.

## User API

Provides the functionality to retrieve all users in a CSV format. The users data is returned as an attachment with the filename 'users.csv'. The code for this service is provided in the UserController class.

## File Upload API

provides the functionality to upload files to a specified directory in the system. The uploaded file is checked against existing files in the directory, and if it already exists, an error is thrown. Otherwise, 
the file is saved asynchronously. The code for this service is contained in the FileService class.

## Test Coverage

The project includes unit tests  to ensure the robustness and reliability of the system. .

## Documentation

Swagger has been used for the documentation of the REST APIs. It provides an interactive documentation where the users can try out the APIs.

- http://localhost:8080/swagger-ui.html
- http://localhost:8080/v3/api-docs

## Running the Project

To run the project, simply follow the steps:

Build the project using 
```bash
mvn clean install.
```
Run the application using:
```bash
java -jar target/<name-of-the-jar>.jar.
```

## Deployment

The application can be easily containerized using the provided Dockerfile. The Dockerfile includes all the necessary steps to build and run the application inside a Docker container.
```bash
docker build -t <name-of-your-image> .
```

```bash
docker run -d --name <name-of-your-container> -p 8080:8080 <name-of-your-image>
```

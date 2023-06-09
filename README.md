# Perinity API

This repository contains the source code for the Perinity API, which is a RESTful API for managing tasks and people in an organization.

## Table of Contents

- [Introduction](#introduction)
- [Technologies](#technologies)
- [API Endpoints](#api-endpoints)
- [Getting Started](#getting-started)




## Introduction

The Perinity API provides endpoints to manage tasks and people within an organization. It allows you to perform CRUD (Create, Read, Update, Delete) operations on tasks and people entities.

## Technologies

The Perinity API is built using the following technologies:

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL

## API Endpoints

The Perinity API provides the following endpoints:


- **GET /pessoas**: List people bringing name, department, total hours spent on tasks.
- **POST /pessoas**: Create a new person.
- **PUT /pessoas/{id}**: Update an existing person.
- **GET /pessoas/gastos**: Searching people by name and time period returns the average hours spent per task.
- **GET /pessoas/listartudo**: Retrieve a list of all person and tasks alocated.
- **DELETE /pessoas/{id}**: Delete a person.
- **GET /tarefas/pendentes**: List 3 tasks that are without an allocated person with the oldest deadlines.
- **PUT /tarefas/alocar/{id}**: Assign a person to a task.
- **PUT /tarefas/finalizar/{id}**: Mark a task as finished.
- **POST /tarefas**: Create a new task.
- **GET /departamentos**: List department and number of people and tasks.


## Getting Started

To get started with the Perinity API, follow these steps:

1. Clone the repository:

   ```shell
   git clone https://github.com/belizario87/perinity-api.git
   
2. Navigate to the project directory:

   ```shell
   cd perinity-api 
3. Build the project using Maven:
   ```shell
   mvn clean install

4. Run the following command to start the Docker containers:
     ```shell
     docker-compose up -d

5. Wait for the containers to initialize. This may take a few minutes.
    
6. Once initialized, the API will be available at.
      ```shell
      http://localhost:8080

7. To stop the Docker containers, run the following command:
      ```shell
      docker-compose down

8. That's it! Now you can explore and use the project.

## Notes

- Make sure you have Docker installed and running on your machine.


9. CConfigure the database connection in the application.properties file:

   ```shell
   spring.datasource.url=jdbc:postgresql://localhost:5432/task_manager
   spring.datasource.username=postgres
   spring.datasource.password=postgres
   
10. Run the aplication:
   ```shell
   mvn spring-boot:run





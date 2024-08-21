
# Project Title - File Storage and Retrieval API

## Overview

This project is a Spring Boot application that provides a RESTful API for storing, retrieving, and managing files. 
It supports storing files in both a database and the file system. The API includes endpoints for uploading files, retrieving files, and downloading files from the file system.

## Features

- Upload files to the database and file system.
- Retrieve all files with metadata (name, type, and access URL).
- Download files by name from the file system.
- Supports cross-origin requests.

## Technologies Used

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **MySQL or PostgreSQL**
- **Maven**
- **Lombok**
- **SLF4J** for logging

## Getting Started

### Prerequisites

- **Java 17** or later installed
- **Maven** installed
- **MySQL** or **PostgreSQL** database setup

### Setup Instructions

1. **Clone the repository:**

   ```bash
   git clone https://github.com/Subhashchandra-Birajdar/UploadFile-image.git
   cd UploadFile-image
   
2. **Configure the database:**

Open the src/main/resources/application.properties (or application.yml) file and configure your database connection.

properties
```
spring.datasource.url=jdbc:postgresql://localhost:5432/your-database-name
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update
```

# File storage location
file.storage.path=C:\\path\\to\\your\\file\\storage\\directory\\

# Start the application:
The application will start on http://localhost:8080


# API Endpoints

### Upload File
- **URL:** `/api/uploadFilesIntoDB`
- **Method:** `POST`
- **Description:** Upload a file to the database and file system.
- **Request:**
  - `file` (multipart/form-data): The file to be uploaded.
- **Response:**
  - `200 OK` with a success message.
#### Example in Screenshot:
![upload Image](https://github.com/user-attachments/assets/1b24c87f-821f-4b6d-b300-d32044ad1789)

### Get All Files With Image Response
- **URL:** `/api/files`
- **Method:** `GET`
- **Description:** Retrieve a list of all files with their metadata.
- **Response:**
  - `200 OK` with a list of files (name, type, URL).
#### Example in Screenshot:
![Get All Images](https://github.com/user-attachments/assets/88c65d25-df40-4b63-83aa-35501ad370b2)

### Download File from File System
- **URL:** `/api/getFilesFromFileSystem/{fileName}`
- **Method:** `GET`
- **Description:** Download a file from the file system by its name.
- **Path Parameter:**
  - `fileName`: The name of the file to download.
- **Response:**
  - `200 OK` with the file's binary data.
#### Example in Screenshot:
![Get - Download Image](https://github.com/user-attachments/assets/8b66c8c1-49a5-4e10-a130-c40aa1b2ce24)


## API Reference

#### Upload File

```http
  POST /api/uploadFilesIntoDB
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| 'file' | `multipart/form-data` | Required. The file to be uploaded. |  

#### Get all images

```http
  GET /api/files
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| 'none' | `string` | **Required**.Retrieves a list of all files with metadata. |

#### Get image by name

```http
  GET /api/getFilesFromFileSystem/{fileName}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. image of name to fetch |


## Project Structure
- **`src/main/java/com/jts/entity/Files.java`:** The entity class representing the files stored in the database.
- **`src/main/java/com/jts/repository/FileRepository.java`:** The repository interface for accessing file data.
- **`src/main/java/com/jts/service/FilesService.java`:** The service layer handling file operations.
- **`src/main/java/com/jts/controller/FilesController.java`:** The REST controller exposing the API endpoints.

## Logging
This project uses **SLF4J** with **Logback** for logging. Logs are written to the console during application execution.
The `FilesController` and `FilesService` classes include log statements to track the flow of operations.

## Contributing
If you'd like to contribute to this project, feel free to fork the repository and submit a pull request. Issues and feature requests are also welcome!



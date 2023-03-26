# Book Shop API
This is a simple Spring Boot application that provides CRUD operations for a Book Shop.

## Getting Started
### Prerequisites

* Java 8 or higher
* Maven

## Running the Application
To run the application, navigate to the project directory and execute the following command:

`mvn spring-boot:run`

The application will start on port 8080. You can access the API by navigating to http://localhost:8080 in your web browser.

## Endpoints
The following endpoints are available in the API:

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/api/books` | Get all books |
| `POST` | `/api/books/{id}` | Add a new book |
| `PUT` | `/api/books/{id}` | Update an existing book | 
| `DELETE` | `/api/books/{id}` | Delete a book by ID |

## Run locally
`git clone https://github.com/gigbat/book-shop.git`

`cd book-shop`

`mvn clean install`

`mvn spring-boot:run`

## Running Tests
To run the tests for this application, navigate to the project directory and execute the following command:

`mvn test`

This will run all the tests in the application and report the results.




# Library-Management
Library Management CRUD with RESTFULL API

Objective

The Digital Library Book Management System is a RESTful API designed to help librarians efficiently manage books by adding, updating, searching, and removing records while maintaining their availability status.

Features

1. Add a Book
Accepts Book ID, Title, Author, Genre, and Availability Status as input.
Stores the book details in a collection.

2. View All Books
Displays a list of all books with their details.

3. Search Book by ID or Title
Allows searching for a book using its ID or title.

4. Update Book Details
Enables modification of book details, such as:

Changing availability status
Updating title or author

5. Delete a Book Record
Removes a book from the catalog.


Constraints
Book ID should be unique.
Title and Author should be non-empty strings.
Availability Status should be either Available or Checked Out.


Technologies Used
Spring Boot (REST API development)
Maven (Build tool)
MySQL (Database management)
Spring Data JPA (Database interaction)


Setup and Installation

Prerequisites
Java 17 or later
Maven
MySQL Database
Git
Postman or any API testing tool

Steps to Run the Project
Clone the repository:
git clone https://github.com/gaurish27/Library-Management.git

Navigate to the project directory:
cd Library-Management

Configure Database:
Open application.properties located in src/main/resources/
Update the database configurations:
spring.datasource.url=jdbc:mysql://localhost:3306/library_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

Build and Run the Application:
mvn spring-boot:run

API Endpoints:
Add a Book: POST /api/books
View All Books: GET /api/books
Search Book by ID: GET /api/books/{id}
Search Book by Title: GET /api/books/search?title={title}
Update Book: PUT /api/books/{id}
Delete Book: DELETE /api/books/{id}

Deployment
The application is deployed at: <Deployment Link> (Replace with actual link)


Challenges Faced & Improvements
Challenges
Setting up database migrations for seamless schema updates.
Ensuring proper validation and error handling.


Future Improvements
Implement a frontend interface for better user interaction.
Add user authentication and authorization.
Improve API security and logging mechanisms.

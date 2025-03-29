package com.example.demo1;
import com.example.demo1.model.Book;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

public class BookTerminal {

    private static final String BASE_URL = "http://localhost:8080/books";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final Scanner scanner = new Scanner(System.in);


    private static void getAllBooks() {
        ResponseEntity<Book[]> response = restTemplate.getForEntity(BASE_URL, Book[].class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            Book[] books = response.getBody();

            System.out.println("\n📚 Book List:");
            System.out.println("------------------------------------------------------------");
            System.out.printf("%-5s | %-25s | %-15s | %-10s\n", "ID", "Title", "Author", "Availability");
            System.out.println("------------------------------------------------------------");

            for (Book book : books) {
                System.out.printf("%-5d | %-25s | %-15s | %-10s\n",
                        book.getId(), book.getTitle(), book.getAuthor(), book.getAvailabilityStatus());
            }
            System.out.println("------------------------------------------------------------");
        } else {
            System.out.println(" Error fetching books: " + response.getStatusCode());
        }
    }

    private static void getBookById(long id) {
        ResponseEntity<Book> response = restTemplate.getForEntity(BASE_URL + "/" + id, Book.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            Book book = response.getBody();

            System.out.println("\nBook Details:");
            System.out.println("------------------------------------------------------------");
            System.out.printf("%-5s | %-25s | %-15s | %-10s\n", "ID", "Title", "Author", "Availability");
            System.out.println("------------------------------------------------------------");
            System.out.printf("%-5d | %-25s | %-15s | %-10s\n",
                    book.getId(), book.getTitle(), book.getAuthor(), book.getAvailabilityStatus());
            System.out.println("------------------------------------------------------------");
        } else {
            System.out.println("Error fetching book details: " + response.getStatusCode());
        }
    }

    private static void addBook() {
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter Availability (Available/Checked Out): ");
        String availabilityStatus = scanner.nextLine();

        Book newBook = new Book(null, title, author, genre, availabilityStatus);
        HttpEntity<Book> request = new HttpEntity<>(newBook);
        ResponseEntity<Book> response = restTemplate.postForEntity(BASE_URL, request, Book.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("✅ Book added successfully!");
        } else {
            System.out.println("Failed to add book.");
        }
    }

    private static void updateBook() {
        System.out.print("Enter Book ID to update: ");
        long id = scanner.nextLong();
        scanner.nextLine();  // Consume newline

        System.out.print("Enter New Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter New Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter New Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter New Availability (Available/Checked Out): ");
        String availabilityStatus = scanner.nextLine();

        Book updatedBook = new Book(id, title, author, genre, availabilityStatus);
        HttpEntity<Book> request = new HttpEntity<>(updatedBook);
        ResponseEntity<Book> response = restTemplate.exchange(BASE_URL + "/" + id, org.springframework.http.HttpMethod.PUT, request, Book.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Book updated successfully!");
        } else {
            System.out.println("Failed to update book.");
        }
    }

    private static void deleteBook(long id) {
        ResponseEntity<String> response = restTemplate.exchange(BASE_URL + "/" + id, org.springframework.http.HttpMethod.DELETE, null, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Book deleted successfully!");
        } else {
            System.out.println("Failed to delete book.");
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Get All Books");
            System.out.println("2. Get Book by ID");
            System.out.println("3. Add a Book");
            System.out.println("4. Update a Book");
            System.out.println("5. Delete a Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    getAllBooks();
                    break;
                case 2:
                    System.out.print("Enter Book ID: ");
                    long bookId = scanner.nextLong();
                    getBookById(bookId);
                    break;
                case 3:
                    addBook();
                    break;
                case 4:
                    updateBook();
                    break;
                case 5:
                    System.out.print("Enter Book ID to delete: ");
                    long deleteId = scanner.nextLong();
                    deleteBook(deleteId);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }

    }
}

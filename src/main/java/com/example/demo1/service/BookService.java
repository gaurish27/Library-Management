package com.example.demo1.service;
import com.example.demo1.model.Book;
import com.example.demo1.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private com.example.demo1.repository.BookRepository BookRepository;
    public List<Book> getAllBooks() {
        return BookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return BookRepository.findById(id);
    }

    public Optional<Book> getBookByTitle(String title) {
        return BookRepository.findByTitle(title);
    }

    public Book addBook(Book book) {
        return BookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        return BookRepository.findById(id).map(book -> {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setGenre(updatedBook.getGenre());
            book.setAvailabilityStatus(updatedBook.getAvailabilityStatus());
            return BookRepository.save(book);
        }).orElse(null);
    }

    public boolean deleteBook(Long id) {
        if (BookRepository.existsById(id)) {
            BookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

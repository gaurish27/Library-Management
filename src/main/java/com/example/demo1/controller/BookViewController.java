package com.example.demo1.controller;
import com.example.demo1.model.Book;
import com.example.demo1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BookViewController {

    @Autowired
    private BookService bookService;

    @GetMapping("/list")
    public String homePage(Model model) {
        List<Book> books = bookService.getAllBooks();
        System.out.println("Books Retrieved: " + books);
        model.addAttribute("books", books);
        return "index";  // Ensures Spring Boot looks for index.html in templates
    }

}

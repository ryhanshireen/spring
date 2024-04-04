package com.ryhan.junitassignment.controller;

import com.ryhan.junitassignment.entity.BookRequest;
import com.ryhan.junitassignment.entity.BookResponse;
import com.ryhan.junitassignment.service.BookService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/{bookId}", consumes = "application/json", produces = "application/json")
    public Object getBookDetails(@PathVariable Long bookId) {
        BookResponse book = bookService.getBookDetails(bookId);
        if (book != null) {
            return book;
        } else {
            return "Book with ID " + bookId + " not found";
        }
    }

    @PostMapping(value = "/addbook", consumes = "application/json", produces = "application/json")
    public BookResponse addNewBook(@RequestBody BookRequest bookRequest) {
        return bookService.addNewBook(bookRequest);
    }
    

}

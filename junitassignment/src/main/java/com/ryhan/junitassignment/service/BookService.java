package com.ryhan.junitassignment.service;

import com.ryhan.junitassignment.entity.BookRequest;
import com.ryhan.junitassignment.entity.BookResponse;
import com.ryhan.junitassignment.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponse getBookDetails(Long bookId) {
        return bookRepository.getBookDetails(bookId);
    }

    public BookResponse addNewBook(BookRequest bookRequest) {
        return bookRepository.addNewBook(bookRequest);
    }
}

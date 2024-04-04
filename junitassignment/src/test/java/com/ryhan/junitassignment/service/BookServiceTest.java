package com.ryhan.junitassignment.service;

import com.ryhan.junitassignment.entity.BookRequest;
import com.ryhan.junitassignment.entity.BookResponse;
import com.ryhan.junitassignment.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBookDetails_Success() {
        Long bookId = 1L;
        BookResponse mockBookResponse = new BookResponse(1L, "Educated", "Tara Westover", "Memoir", 4.5);
        when(bookRepository.getBookDetails(anyLong())).thenReturn(mockBookResponse);
        BookResponse result = bookService.getBookDetails(bookId);
        assertEquals(mockBookResponse, result);
    }

    @Test
    void testAddNewBook_Success() {
        BookRequest mockBookRequest = new BookRequest(1L, "Educated", "Tara Westover", "Memoir", 4.5);
        BookResponse mockBookResponse = new BookResponse(1L, "Educated", "Tara Westover", "Memoir", 4.5);
        when(bookRepository.addNewBook(any(BookRequest.class))).thenReturn(mockBookResponse);
        BookResponse result = bookService.addNewBook(mockBookRequest);
        assertEquals(mockBookResponse, result);
    }
}

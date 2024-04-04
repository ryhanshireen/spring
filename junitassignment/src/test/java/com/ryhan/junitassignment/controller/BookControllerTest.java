package com.ryhan.junitassignment.controller;

import com.ryhan.junitassignment.entity.BookRequest;
import com.ryhan.junitassignment.entity.BookResponse;
import com.ryhan.junitassignment.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    public BookControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBookDetails_Success() {
        Long bookId = 1L;
        BookResponse mockBookResponse = new BookResponse(1L, "Educated", "Tara Westover", "Memoir", 4.5);
        when(bookService.getBookDetails(bookId)).thenReturn(mockBookResponse);
        Object result = bookController.getBookDetails(bookId);
        assertEquals(mockBookResponse, result);
        verify(bookService, times(1)).getBookDetails(bookId);
    }

    @Test
    void testGetBookDetails_NotFound() {
        Long bookId = 2L;
        when(bookService.getBookDetails(bookId)).thenReturn(null);
        Object result = bookController.getBookDetails(bookId);
        assertEquals("Book with ID 2 not found", result);
        verify(bookService, times(1)).getBookDetails(bookId);
    }

    @Test
    void testAddNewBook_Success() {
    	BookRequest mockBookRequest = new BookRequest(1L, "Educated", "Tara Westover", "Memoir", 4.5);
        BookResponse mockBookResponse = new BookResponse(1L, "Educated", "Tara Westover", "Memoir", 4.5);
        when(bookService.addNewBook(mockBookRequest)).thenReturn(mockBookResponse);
        BookResponse result = bookController.addNewBook(mockBookRequest);
        assertEquals(mockBookResponse, result);
        verify(bookService, times(1)).addNewBook(mockBookRequest);
    }
}

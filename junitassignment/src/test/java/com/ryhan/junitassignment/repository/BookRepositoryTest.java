package com.ryhan.junitassignment.repository;

import com.ryhan.junitassignment.entity.BookRequest;
import com.ryhan.junitassignment.entity.BookResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class BookRepositoryTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBookDetails() {
        Long bookId = 1L;
        BookResponse mockBookResponse = new BookResponse(1L, "Educated", "Tara Westover", "Memoir", 4.5);

        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(BookResponse.class)))
                .thenReturn(new ResponseEntity<>(mockBookResponse, HttpStatus.OK));
        BookResponse result = bookRepository.getBookDetails(bookId);
        assertEquals(mockBookResponse, result);
    }

    @Test
    void testAddNewBook() {
    	BookRequest mockBookRequest = new BookRequest(1L, "Educated", "Tara Westover", "Memoir", 4.5);
        BookResponse mockBookResponse = new BookResponse(1L, "Educated", "Tara Westover", "Memoir", 4.5);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(BookResponse.class)))
                .thenReturn(new ResponseEntity<>(mockBookResponse, HttpStatus.OK));
        BookResponse result = bookRepository.addNewBook(mockBookRequest);
        assertEquals(mockBookResponse, result);
    }
}

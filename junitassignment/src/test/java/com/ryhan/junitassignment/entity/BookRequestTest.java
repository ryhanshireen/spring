package com.ryhan.junitassignment.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BookRequestTest {

    @Test
    void testConstructorAndGetters() {
        Long id = 1L;
        String name = "Educated";
        String author = "Tara Westover";
        String description = "Memoir";
        Double rating = 4.5;
        BookRequest bookRequest = new BookRequest(id, name, author, description, rating);
        
        assertEquals(id, bookRequest.getId());
        assertEquals(name, bookRequest.getName());
        assertEquals(author, bookRequest.getAuthor());
        assertEquals(description, bookRequest.getDescription());
        assertEquals(rating, bookRequest.getRating());
    }

    @Test
    void testSetters() {
    	BookRequest bookRequest = new BookRequest( 1L, "Educated", "Tara Westover", "Memoir", 4.5);
        bookRequest.setId(1L);
        bookRequest.setName("Educated");
        bookRequest.setAuthor("Tara Westover");
        bookRequest.setDescription("Memoir");
        bookRequest.setRating(4.5);
        assertEquals(1L, bookRequest.getId());
        assertEquals("Educated", bookRequest.getName());
        assertEquals("Tara Westover", bookRequest.getAuthor());
        assertEquals("Memoir", bookRequest.getDescription());
        assertEquals(4.5, bookRequest.getRating());
    }

    @Test
    void testDefaultConstructor() {
    	BookRequest bookRequest = new BookRequest();
        assertNull(bookRequest.getId());
        assertNull(bookRequest.getName());
        assertNull(bookRequest.getAuthor());
        assertNull(bookRequest.getDescription());
        assertNull(bookRequest.getRating());
    }
}

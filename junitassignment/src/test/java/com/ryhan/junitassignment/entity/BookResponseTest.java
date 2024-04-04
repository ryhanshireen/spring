package com.ryhan.junitassignment.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookResponseTest {

    @Test
    void testConstructorAndGetters() {
    	Long id = 1L;
        String name = "Educated";
        String author = "Tara Westover";
        String description = "Memoir";
        Double rating = 4.5;
        BookResponse bookResponse = new BookResponse(1L, "Educated", "Tara Westover", "Memoir", 4.5);
        assertEquals(id, bookResponse.getId());
        assertEquals(name, bookResponse.getName());
        assertEquals(author, bookResponse.getAuthor());
        assertEquals(description, bookResponse.getDescription());
        assertEquals(rating, bookResponse.getRating());
    }

    @Test
    void testSetters() {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(1L);
        bookResponse.setName("Educated");
        bookResponse.setAuthor("Tara Westover");
        bookResponse.setDescription("Memoir");
        bookResponse.setRating(4.5);
        assertEquals(1L, bookResponse.getId());
        assertEquals("Educated", bookResponse.getName());
        assertEquals("Tara Westover", bookResponse.getAuthor());
        assertEquals("Memoir", bookResponse.getDescription());
        assertEquals(4.5, bookResponse.getRating());
    }

    @Test
    void testDefaultConstructor() {
        BookResponse bookResponse = new BookResponse();
        assertEquals(null, bookResponse.getId());
        assertEquals(null, bookResponse.getName());
        assertEquals(null, bookResponse.getAuthor());
        assertEquals(null, bookResponse.getDescription());
        assertEquals(null, bookResponse.getRating());
    }
}

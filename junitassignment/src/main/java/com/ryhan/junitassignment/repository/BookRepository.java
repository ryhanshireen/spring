package com.ryhan.junitassignment.repository;

import com.ryhan.junitassignment.entity.BookRequest;
import com.ryhan.junitassignment.entity.BookResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class BookRepository {

    @Value("${downstream.service.url}")
    private String downstreamServiceUrl;

    private final RestTemplate restTemplate;

    public BookRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    String url = "http://localhost:9090/books" ;

    public BookResponse getBookDetails(Long bookId) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        

//        String url = downstreamServiceUrl + "/get" + bookId;
    

        HttpEntity<?> bookDetailsRequest = new HttpEntity<>(requestHeaders);

        return restTemplate.exchange(url, HttpMethod.GET, bookDetailsRequest, BookResponse.class).getBody();
    }

    public BookResponse addNewBook(BookRequest bookRequest) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

//       String url = downstreamServiceUrl + "/post";


        HttpEntity<BookRequest> addBookRequest = new HttpEntity<>(bookRequest, requestHeaders);

        return restTemplate.exchange(url, HttpMethod.POST, addBookRequest, BookResponse.class).getBody();
    }
}

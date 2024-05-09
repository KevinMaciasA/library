package com.kma.library.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.kma.library.http.API;
import com.kma.library.models.Book;
import com.kma.library.models.Response;

public class BooksAPI extends API {
  private ObjectMapper mapper = new ObjectMapper();

  public BooksAPI() {
    super("https://gutendex.com/");
  }

  private List<Book> fetchResults(String fullPath)
      throws IOException, InterruptedException, JsonProcessingException, JsonMappingException {
    var json = fetch(fullPath);
    var response = mapper.readValue(json, Response.class);
    return response.results();
  }

  public List<Book> getBooks() throws IOException, InterruptedException {
    var path = "/books";
    var fullPath = baseUrl + path;
    return fetchResults(fullPath);
  }

  public Optional<Book> searchBook(String pattern) throws IOException, InterruptedException {
    var path = "/books?search=";
    var normPattern = normalize(pattern);
    var fullPath = baseUrl + path + normPattern;
    var books = fetchResults(fullPath);
    return books
        .stream()
        .filter(book -> book.title().toLowerCase().contains(pattern.toLowerCase()))
        .findFirst();
  }

  private String normalize(String string) {
    return string.replace(" ", "%20");
  }
}

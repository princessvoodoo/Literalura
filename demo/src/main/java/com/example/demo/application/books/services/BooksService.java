package com.example.demo.application.books.services;

import java.util.List;

import com.example.demo.domain.books.Book;

public interface BooksService {
    public List<Book> getBookByTitle(String title);
    public List<Book> getAllBooks();
    public Book getBookById(Long id);
    public List<Book> getBooksByAuthor(String author);
    public Book save(Book book);
    public List<Book> getBooksByLanguage(String language);
}

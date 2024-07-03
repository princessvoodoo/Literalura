package com.example.demo.domain.books.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.books.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Long>{
    
    Optional<Book> findById(Long id);

    Optional<Book> findByTitle(String title);

    List<Book> findAll();

    @Query("SELECT b FROM Book b JOIN b.authors a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Book> findByAuthor(String author);

    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Book> findByPartialTitle(String title);

    @Query("SELECT b FROM Book b WHERE LOWER(b.languages) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Book> findByLanguage(String language);
    
}

package com.example.demo.application.books.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.application.persons.services.PersonsService;
import com.example.demo.domain.books.Book;
import com.example.demo.domain.books.BookFactory;
import com.example.demo.domain.books.repository.BooksRepository;
import com.example.demo.domain.persons.Person;
import com.example.demo.util.RequestUtil;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BooksServiceImpl implements BooksService {
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private PersonsService personsService;

    private void saveBooks(List<Book> books) {
        booksRepository.saveAll(books);
    }

    @Override
    public List<Book> getBookByTitle(String title) {
        String json = RequestUtil.doGetRequest("https://gutendex.com/books?search=" + title);
        List<Book> booksList = new ArrayList<>();
        List<Book> booksListR = booksRepository.findByPartialTitle(title);
        Set<String> existingAuthors = booksListR.stream().map(Book::getAuthors).flatMap(List::stream)
                .map(Person::getName).collect(Collectors.toSet());
        System.out.println("books " + booksListR.toString());
        System.out.println("authors " + existingAuthors.toString());
        Set<String> existingTitles = booksListR.stream().map(Book::getTitle).collect(Collectors.toSet());
        List<Person> authors = new ArrayList<>();

        JSONArray books = new JSONObject(json).getJSONArray("results");

        for (int i = 0; i < books.length(); i++) {
            Book aux = BookFactory.mapperFromJSON(books.getJSONObject(i));
            if (!existingTitles.contains(aux.getTitle())
                    && aux.getTitle().toLowerCase().contains(title.toLowerCase())) {
                booksList.add(aux);
                aux.getAuthors().forEach(author -> {
                    if (!existingAuthors.contains(author.getName()) && !authors.contains(author)) {
                        authors.add(author);
                    }
                });
            }

        }

        Map<String, Person> authorNameToIdMap = personsService.saveAll(authors).stream()
                .collect(Collectors.toMap(Person::getName, person -> person));

        for (Book b : booksList) {
            List<Person> authorsBook = b.getAuthors();
            authorsBook.replaceAll(author -> authorNameToIdMap.getOrDefault(author.getName(), author));
        }

        saveBooks(booksList);
        booksListR.addAll(booksList);

        return booksListR;
    }

    @Override
    public List<Book> getAllBooks() {
        return booksRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        Book bookR = booksRepository.findById(id).orElse(null);
        if (bookR != null) {
            return bookR;
        }
        Book book = BookFactory.mapperFromJSON(new JSONObject(
                RequestUtil.doGetRequest("https://gutendex.com/books/" + id.toString() + ".json")));
        List<String> existingAuthors = personsService
                .getPersonsByNames(book.getAuthors().stream().map(Person::getName).collect(Collectors.toList()))
                .stream().map(Person::getName).collect(Collectors.toList());
        List<Person> authors = book.getAuthors().stream().filter(author -> !existingAuthors.contains(author.getName()))
                .collect(Collectors.toList());

        Map<String, Person> authorNameToIdMap = personsService.saveAll(authors).stream()
                .collect(Collectors.toMap(Person::getName, person -> person));

        authors.replaceAll(author -> authorNameToIdMap.getOrDefault(author.getName(), author));

        book.setAuthors(authors);

        save(book);
        return book;
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        return booksRepository.findByAuthor(author);
    }

    @Override
    public Book save(Book book) {
        booksRepository.findByTitle(book.getTitle()).ifPresent(b -> {
            throw new IllegalArgumentException("Book already exists");
        });
        return booksRepository.save(book);
    }

    @Override
    public List<Book> getBooksByLanguage(String language) {
        return booksRepository.findByLanguage(language);
    }

}

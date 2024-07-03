package com.example.demo.domain.books;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.example.demo.domain.persons.Person;
import com.example.demo.domain.persons.PersonFactory;

public class BookFactory {

    private BookFactory() {
    }

    public static Book createBook(String title, List<Person> authors, List<String> languages, Long downloadCount) {
        return Book.builder()
                .title(title)
                .authors(authors)
                .languages(languages)
                .downloadCount(downloadCount)
                .build();
    }

    public static Book createBook(Long id, String title, List<Person> authors, List<String> languages,
            Long downloadCount) {
        Book aux = createBook(title, authors, languages, downloadCount);
        aux.setId(id);
        return aux;
    }

    public static Book mapperFromJSON(JSONObject json) {
        List<Person> authors = new ArrayList<>();
        json.getJSONArray("authors").forEach(author -> {
            authors.add(PersonFactory.mapperFromJSON((JSONObject) author));
        });
        List<String> languages = new ArrayList<>();
        json.getJSONArray("languages").forEach(language -> {
            languages.add((String) language);
        });
        return createBook(
                json.getLong("id"),
                json.getString("title"),
                authors,
                languages,
                json.getLong("download_count"));
    }

}

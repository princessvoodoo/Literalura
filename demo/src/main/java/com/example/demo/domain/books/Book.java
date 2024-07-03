package com.example.demo.domain.books;

import java.util.List;

import com.example.demo.domain.persons.Person;
import com.example.demo.util.converter.StringListConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String title;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Person> authors;

    @Convert(converter = StringListConverter.class)
    @Column(nullable = false)
    private List<String> languages;

    private Long downloadCount;
}

package com.example.demo.domain.persons.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.persons.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findAll();

    @Query("SELECT p FROM Person p WHERE (p.birthYear IS NOT NULL AND p.birthYear < :year) AND (p.deathYear IS NOT NULL AND p.deathYear >= :year)")
    List<Person> findByPersonAliveByYear(Integer year);

    Optional<Person> findByName(String name);

    List<Person> findByNameIn(List<String> names);
}

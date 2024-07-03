package com.example.demo.application.persons.services;

import java.util.List;

import com.example.demo.domain.persons.Person;

public interface PersonsService {
    public List<Person> getAllPersons();
    public List<Person> getPersonAliveByYear(Integer year);
    public Person save(Person person);
    public List<Person> saveAll(List<Person> persons);
    public Person getPersonByName(String name);
    public List<Person> getPersonsByNames(List<String> names);
}

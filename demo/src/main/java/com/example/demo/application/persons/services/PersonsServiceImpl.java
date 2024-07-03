package com.example.demo.application.persons.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.persons.Person;
import com.example.demo.domain.persons.repository.PersonRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PersonsServiceImpl implements PersonsService {
    @Autowired
    private PersonRepository personRepository;

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Person getPersonByName(String name) {
        return personRepository.findByName(name).orElseThrow(
                () -> new RuntimeException("Person with name " + name + " not found"));
    }

    public List<Person> getPersonsByNames(List<String> names) {
        return personRepository.findByNameIn(names);
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public List<Person> getPersonAliveByYear(Integer year) {
        return personRepository.findByPersonAliveByYear(year);
    }

    @Override
    public List<Person> saveAll(List<Person> persons) {
        return personRepository.saveAll(persons);
    }

}

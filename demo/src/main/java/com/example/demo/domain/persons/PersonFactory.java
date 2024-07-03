package com.example.demo.domain.persons;

import org.json.JSONObject;

public class PersonFactory {

    private PersonFactory() {
    }

    public static Person createPerson(String name, Integer birthYear, Integer deathYear) {

        return Person.builder()
                .name(name)
                .birthYear(birthYear)
                .deathYear(deathYear)
                .build();
    }

    public static Person mapperFromJSON(JSONObject json) {
        Integer deathYear = json.get("death_year") instanceof Integer ? json.getInt("death_year") : null;
        Integer birthYear = json.get("birth_year") instanceof Integer ? json.getInt("birth_year") : null;
        return createPerson(
                json.getString("name"),
                birthYear,
                deathYear);
    }
}

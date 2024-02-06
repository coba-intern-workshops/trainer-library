package com.commerzbank.library.repository;

import com.commerzbank.library.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepositoryImpl implements RepositoryIfc<Person>{

    private List<Person> persons = new ArrayList<>();

    @Override
    public List<Person> findAll() {
        return persons;
    }

    @Override
    public Person save(Person object) {
        if (object == null){
            throw new IllegalArgumentException("Cannot save null object");
        }
        persons.add(object);
        return object;
    }
}

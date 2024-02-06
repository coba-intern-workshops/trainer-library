package com.commerzbank.library.service;

import com.commerzbank.library.converter.PersonConverter;
import com.commerzbank.library.dto.PersonDto;
import com.commerzbank.library.repository.PersonRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepositoryImpl personRepository;
    private final PersonConverter personConverter;

    public List<PersonDto> findAllPersons() {
        return personRepository.findAll()
                .stream()
                .map(personConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    public List<PersonDto> findBooks(PersonSearchCriteria searchCriteria) {
        return personRepository.findAll().stream()
                .filter(person -> person.getFirstName().equals(searchCriteria.getFirstName()) &&
                        person.getLastName().equals((searchCriteria.getLastName())))
                .map(personConverter::convertFromEntity)
                .collect(Collectors.toList());
    }
}

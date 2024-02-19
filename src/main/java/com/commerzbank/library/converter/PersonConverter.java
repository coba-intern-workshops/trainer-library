package com.commerzbank.library.converter;

import com.commerzbank.library.dto.UserTypeDto;
import com.commerzbank.library.model.Person;
import com.commerzbank.library.dto.PersonDto;
import com.commerzbank.library.model.UserType;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PersonConverter extends Converter<PersonDto , Person>{
    public PersonConverter() {
        super(PersonConverter::convertToEntity, PersonConverter::convertToDto);
    }

    private static PersonDto convertToDto(Person person){
        return new PersonDto(person.getFirstName(), person.getLastName(), UserTypeDto.valueOf(person.getUserType().toString()));
    }

    private static Person convertToEntity(PersonDto dto){
        return new Person(UUID.randomUUID(), dto.getFirstName(), dto.getFirstName(), UserType.valueOf(dto.getUserType().toString()));
    }
}

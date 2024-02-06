package com.commerzbank.library.control;

import com.commerzbank.library.dto.PersonDto;
import com.commerzbank.library.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/persons")
    public ResponseEntity<List<PersonDto>> findAllPersons(){
        return ResponseEntity.ok(personService.findAllPersons());
    }
}

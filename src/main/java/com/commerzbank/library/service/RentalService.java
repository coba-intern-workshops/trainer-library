package com.commerzbank.library.service;

import com.commerzbank.library.converter.RentalConverter;
import com.commerzbank.library.dto.RentalDto;
import com.commerzbank.library.model.Rental;
import com.commerzbank.library.repository.Repository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RentalService {

    private final Repository<Rental> rentalRepository;
    private final RentalConverter rentalConverter;
    public List<RentalDto> findRentals (RentalSearchCriteria searchCriteria){
        return rentalRepository.findAll().stream()
                .filter(rental -> rental.getPerson().getFirstName().equals(searchCriteria.getFirstName()) &&
                rental.getPerson().getLastName().equals((searchCriteria.getLastName())) )
                .map(rentalConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

}

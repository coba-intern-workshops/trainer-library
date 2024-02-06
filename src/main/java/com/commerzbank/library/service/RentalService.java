package com.commerzbank.library.service;

import com.commerzbank.library.converter.RentalConverter;
import com.commerzbank.library.dto.RentalDto;
import com.commerzbank.library.model.Rental;
import com.commerzbank.library.repository.RentalRepositoryImpl;
import com.commerzbank.library.repository.RepositoryIfc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepositoryImpl rentalRepository;
    private final RentalConverter rentalConverter;

    public List<RentalDto> findAllRentals(){
        return rentalRepository.findAll().stream()
                .map(rentalConverter::convertFromEntity)
                .collect(Collectors.toList());
    }
    public List<RentalDto> findRentals(RentalSearchCriteria searchCriteria){
        return rentalRepository.findAll().stream()
                .filter(rental -> rental.getPerson().getFirstName().equals(searchCriteria.getFirstName()) &&
                rental.getPerson().getLastName().equals((searchCriteria.getLastName())))
                .map(rentalConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

}

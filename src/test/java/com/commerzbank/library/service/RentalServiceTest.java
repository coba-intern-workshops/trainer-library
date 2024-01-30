package com.commerzbank.library.service;

import com.commerzbank.library.converter.RentalConverter;
import com.commerzbank.library.dto.RentalDto;
import com.commerzbank.library.model.*;
import com.commerzbank.library.repository.RentalRepositoryImpl;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class RentalServiceTest implements WithAssertions {

    //TODO use mock with Mockito
    private RentalRepositoryImpl  rentalRepository;
    private RentalService rentalService;

    @BeforeEach
    void setUp(){
        rentalRepository = new RentalRepositoryImpl();
        rentalService = new RentalService(rentalRepository, new RentalConverter());
    }

    @Test
    void shouldReturnListOfRentals(){
        Rental testRental1 = getTestRental();
        Rental testRental2 = getTestRental();

        rentalRepository.save(testRental1);
        rentalRepository.save(testRental2);

        List<RentalDto> rentals = rentalService.findRentals(new RentalSearchCriteria("Joe", "Doe"));
        int EXPECTED_LIST_SIZE =2;
        assertThat(rentals).isNotNull().isNotEmpty();
        assertThat(rentals.size()).isEqualTo(EXPECTED_LIST_SIZE);
    }


    private static Rental getTestRental() {
        Book book = new Book(UUID.randomUUID(), "testTitle", "testAuthor", BookStatus.AVAILABLE);
        Person person = new Person(UUID.randomUUID(), "Joe", "Doe", UserType.USER);
        return new Rental(book, person, LocalDate.now(), LocalDate.now().plusWeeks(4));
    }
}

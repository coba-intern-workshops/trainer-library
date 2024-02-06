package com.commerzbank.library.service;

import com.commerzbank.library.converter.RentalConverter;
import com.commerzbank.library.dto.RentalDto;
import com.commerzbank.library.model.*;
import com.commerzbank.library.repository.RentalRepositoryImpl;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RentalServiceTest implements WithAssertions {

    @Mock
    private RentalRepositoryImpl rentalRepository;
    @Spy
    private RentalConverter rentalConverter;
    @InjectMocks
    private RentalService rentalService;

    Rental testRental1;
    Rental testRental2;

    @BeforeEach
    void setUp(){
        rentalRepository = mock(RentalRepositoryImpl.class);
        testRental1 = getTestRental();
        testRental2 = getTestRental();
    }

    @Test
    void shouldReturnListOfRentals(){
        when(rentalRepository.findAll()).thenReturn(List.of(testRental1,testRental2));
        List<RentalDto> rentals = rentalService.findRentals(new RentalSearchCriteria("Joe", "Doe"));
        int EXPECTED_LIST_SIZE =2;
        assertThat(rentals).isNotNull().isNotEmpty();
        assertThat(rentals.size()).isEqualTo(EXPECTED_LIST_SIZE);
        assertThat(rentals.get(0)).isNotNull();
        verify(rentalConverter,times(2)).convertFromEntity(any());
    }

    private static Rental getTestRental() {
        Book book = new Book(UUID.randomUUID(), "testTitle", "testAuthor", BookStatus.AVAILABLE);
        Person person = new Person(UUID.randomUUID(), "Joe", "Doe", UserType.USER);
        return new Rental(book, person, LocalDate.now(), LocalDate.now().plusWeeks(4));
    }
}

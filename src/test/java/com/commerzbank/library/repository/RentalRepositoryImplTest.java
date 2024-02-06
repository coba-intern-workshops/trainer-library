package com.commerzbank.library.repository;

import com.commerzbank.library.model.*;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RentalRepositoryImplTest implements WithAssertions {

    private RepositoryIfc<Rental> repository;

    @BeforeEach
    void setUp() {
        repository = new RentalRepositoryImpl();
    }
    @Test
    void shouldReturnEmptyListForNoElementsOnList() {
        assertThat(repository).isNotNull();
        assertThat(repository.findAll()).isNotNull().isEmpty();
    }

    @Test
    void shouldAddRentalToList() {
        Rental rental = getTestRental();
        repository.save(rental);

        assertThat(repository.findAll()).isNotNull();
        int EXPECTED_LIST_SIZE_WHEN_ADD = 1;
        assertThat(repository.findAll().size()).isEqualTo(EXPECTED_LIST_SIZE_WHEN_ADD);
    }

    @Test
    void shouldCreatedObjectBeSameAsSend() {
        Rental rental = getTestRental();

        Rental rentalFromRepository = repository.save(rental);

        assertEquals(rental, rentalFromRepository);

        assertThat(rental).isEqualTo(rentalFromRepository);

        assertThat(rentalFromRepository.getBook())
                .matches(book -> book.getTitle().equals("testTitle"))
                .matches(book -> book.getAuthor().equals("testAuthor"));

        assertThat(rentalFromRepository.getPerson().getFirstName())
                .startsWith("Jo")
                .contains("e")
                .doesNotContain("Doe");

        assertThat(rentalFromRepository.getRentedOn())
                .as("Checking rental date for user %s %s", rentalFromRepository.getPerson().getFirstName(), rentalFromRepository.getPerson().getLastName() )
                .withFailMessage("Should be " + LocalDate.now())
                .isEqualTo(LocalDate.now());
    }

    @Test
    void shouldThrowIAEWhenNullPassed() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> repository.save(null))
                .withMessage("Cannot save null object");
    }

    private static Rental getTestRental() {
        Book book = new Book(UUID.randomUUID(), "testTitle", "testAuthor", BookStatus.AVAILABLE);
        Person person = new Person(UUID.randomUUID(), "Joe", "Doe", UserType.USER);
        return new Rental(book, person, LocalDate.now(), LocalDate.now().plusWeeks(4));
    }

}
package com.commerzbank.library.control;

import com.commerzbank.library.dto.RentalDto;
import com.commerzbank.library.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//TODO:
// tworzenie - walidacja czy nie wyporzyczona
// kasowanie - zwalnianie stanu - aktualizacja statusu i wryfikacja czy przpisana do tego usera
// przedluzanie - zmiana czasu oddania - zawsze na 7 dni - weryfikacja czy przypisana
@RestController
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    @GetMapping("/rentals")
    public ResponseEntity<List<RentalDto>> findAllRentals(){
        return ResponseEntity.ok(rentalService.findAllRentals());
    }
}

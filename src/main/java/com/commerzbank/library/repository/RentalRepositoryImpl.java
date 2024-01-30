package com.commerzbank.library.repository;

import com.commerzbank.library.model.Rental;

import java.util.ArrayList;
import java.util.List;

public class RentalRepositoryImpl implements Repository<Rental> {

    private List<Rental> rentals = new ArrayList<>();

    @Override
    public List<Rental> findAll() {
        return rentals;
    }

    @Override
    public Rental save(Rental object) {
        if (object == null){
            throw new IllegalArgumentException("Cannot save null object");
        }
        rentals.add(object);
        return object;
    }
}

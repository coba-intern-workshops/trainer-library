package com.commerzbank.library.service;

import com.commerzbank.library.converter.BookConverter;
import com.commerzbank.library.converter.BookCreateConverter;
import com.commerzbank.library.dto.BookDeleteDto;
import com.commerzbank.library.dto.BookCreateDto;
import com.commerzbank.library.dto.BookDto;
import com.commerzbank.library.exception.RecordNotFoundException;
import com.commerzbank.library.exception.RequestValidationException;
import com.commerzbank.library.model.Book;
import com.commerzbank.library.model.BookStatus;
import com.commerzbank.library.repository.BookRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepositoryImpl bookRepository;
    private final BookConverter bookConverter;
    private final BookCreateConverter bookCreateConverter;

    public List<BookDto> findAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    public List<BookDto> findBooks(BookSearchCriteria searchCriteria) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getAuthor().contains(searchCriteria.getAuthor()))
                .map(bookConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    public BookDto saveBook(BookCreateDto bookCreateDto){
        var result = bookRepository.save(bookCreateConverter.convertFromDto(bookCreateDto));
        return bookConverter.convertFromEntity(result);
    }

    public BookDto deleteBook(String id, BookDeleteDto bookDeleteDto) {
        //TODO: walidacja DTO dla request body przed wywołaniem metody
        if (bookDeleteDto.getStatus() != BookStatus.DELETED) {
            throw new RequestValidationException("Status DELETED required");
        }
        Optional<Book> book = bookRepository.findById(UUID.fromString(id));
        book.orElseThrow(() -> new RecordNotFoundException("Book with id" + id + "not exist"));
        book.ifPresent(update -> update.setBookStatus(bookDeleteDto.getStatus()));

        return bookConverter.convertFromEntity(book.get());
    }
}

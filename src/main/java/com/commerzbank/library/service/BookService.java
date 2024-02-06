package com.commerzbank.library.service;

import com.commerzbank.library.converter.BookConverter;
import com.commerzbank.library.dto.BookDto;
import com.commerzbank.library.model.Book;
import com.commerzbank.library.repository.BookRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepositoryImpl bookRepository;
    private final BookConverter bookConverter;

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

    public BookDto saveBook(BookDto bookDto){
        var result = bookRepository.save(bookConverter.convertFromDto(bookDto));
        return bookConverter.convertFromEntity(result);
    }

}

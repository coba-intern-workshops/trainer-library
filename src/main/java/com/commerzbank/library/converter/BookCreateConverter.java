package com.commerzbank.library.converter;

import com.commerzbank.library.dto.BookCreateDto;
import com.commerzbank.library.model.Book;
import com.commerzbank.library.model.BookStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BookCreateConverter extends Converter<BookCreateDto, Book> {
    public BookCreateConverter() {
        super(BookCreateConverter::convertToEntity, BookCreateConverter::convertToDto);
    }


    private static BookCreateDto convertToDto(Book book) {
        return new BookCreateDto(book.getTitle(), book.getAuthor());
    }

    private static Book convertToEntity(BookCreateDto dto) {
        return new Book(UUID.randomUUID(), dto.getTitle(), dto.getAuthor(), BookStatus.AVAILABLE);
    }
}

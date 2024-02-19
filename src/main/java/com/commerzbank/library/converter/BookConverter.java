package com.commerzbank.library.converter;

import com.commerzbank.library.dto.BookDto;
import com.commerzbank.library.dto.BookStatusDto;
import com.commerzbank.library.model.Book;
import com.commerzbank.library.model.BookStatus;
import org.springframework.stereotype.Component;


@Component
public class BookConverter extends Converter<BookDto, Book>{
    public BookConverter() {
        super(BookConverter::convertToEntity, BookConverter::convertToDto);
    }

    private static BookDto convertToDto(Book book){
        return new BookDto(book.getId(), book.getTitle(), book.getAuthor(), BookStatusDto.valueOf(book.getBookStatus().toString()));
    }

    private static Book convertToEntity(BookDto dto){
        return new Book(dto.getId(), dto.getTitle(), dto.getAuthor(), BookStatus.valueOf(dto.getBookStatus().toString()));
    }
}

package com.commerzbank.library.service;

import com.commerzbank.library.converter.BookConverter;
import com.commerzbank.library.converter.BookCreateConverter;
import com.commerzbank.library.dto.BookCreateDto;
import com.commerzbank.library.dto.BookDeleteDto;
import com.commerzbank.library.dto.BookDto;
import com.commerzbank.library.dto.BookStatusDto;
import com.commerzbank.library.model.BookStatus;
import com.commerzbank.library.repository.BookRepositoryImpl;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class BookServiceTest implements WithAssertions {

    private BookService service;

    @BeforeEach
    protected void setUp() {
        service = new BookService(new BookRepositoryImpl(), new BookConverter(), new BookCreateConverter());
    }

    @Test
    void shouldDeleteBook() {
        BookDto book = service.saveBook(BookCreateDto.builder().build());

        BookDto deletedBook = service.deleteBook(book.getId().toString(), BookDeleteDto.builder().status(BookStatus.DELETED).build());

        assertThat(deletedBook).isNotNull();
        assertThat(deletedBook.getBookStatus()).isEqualTo(BookStatusDto.DELETED);
    }

    @Test
    void shouldThrowExceptionWhenBookNotExist() {
            //TODO
    }

    @Test
    void shouldThrowExceptionWhenStatusDifferentThanDeleted() {
            //TODO
    }
}

package com.commerzbank.library.dto;

import com.commerzbank.library.model.BookStatus;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class BookDto {
    private String title;
    private String author;
    private BookStatusDto bookStatus;
}

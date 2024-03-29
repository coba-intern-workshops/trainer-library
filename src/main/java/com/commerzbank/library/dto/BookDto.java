package com.commerzbank.library.dto;

import lombok.*;

import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class BookDto {
    private UUID id;
    private String title;
    private String author;
    private BookStatusDto bookStatus;
}

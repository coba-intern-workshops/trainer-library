package com.commerzbank.library.control;

import com.commerzbank.library.dto.BookDto;
import com.commerzbank.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//TODO:
// - endpoint do wyszukiwania po
// - endpoint do usuwania
// - dodanie tych samych endpointów do pozostałych kontrolerów

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> findAllBooks(){
        return ResponseEntity.ok(bookService.findAllBooks());
    }

    @PostMapping("/books")
    public ResponseEntity<BookDto> saveBook(@RequestBody BookDto bookDto){
        return ResponseEntity.ok(bookService.saveBook(bookDto));
    }
}

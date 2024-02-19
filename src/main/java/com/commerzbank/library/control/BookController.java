package com.commerzbank.library.control;

import com.commerzbank.library.dto.BookDeleteDto;
import com.commerzbank.library.dto.BookCreateDto;
import com.commerzbank.library.dto.BookDto;
import com.commerzbank.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//TODO:
// - endpoint do wyszukiwania po nazwie/autorze

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> findAllBooks(){
        return ResponseEntity.ok(bookService.findAllBooks());
    }

    @PostMapping
    public ResponseEntity<BookDto> saveBook(@RequestBody BookCreateDto bookCreateDto){
        return ResponseEntity.ok(bookService.saveBook(bookCreateDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable String id,
                                                    @RequestBody BookDeleteDto bookDeleteDto) {
        return ResponseEntity.ok(bookService.deleteBook(id, bookDeleteDto));
    }
}

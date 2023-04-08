package com.practice.projectlibrary.controller;

import com.practice.projectlibrary.dto.BookDTO;
import com.practice.projectlibrary.dto.request.BookRequest;
import com.practice.projectlibrary.service.IBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final IBookService bookService;


    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> books() {
        return bookService.books();
    }

    @PostMapping("/add-list-book")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> addListBook(@RequestBody List<@Valid BookRequest> bookRequest) {
        return bookService.addListBook(bookRequest);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO addBook(@Valid @RequestBody BookRequest bookRequest) {
        return bookService.addBook(bookRequest);
    }

    @PostMapping("/{slug}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO updateBook(@PathVariable("slug") String slug, @PathVariable("id") Long id, @Valid @RequestBody BookRequest bookRequest) {
        return bookService.updateBook(slug, id, bookRequest);
    }

    @DeleteMapping("/{slug}/{id}")
    public BookDTO deleteBook(@PathVariable("slug") String slug, @PathVariable("id") Long id) {
        return bookService.deleteBook(slug, id);
    }


}

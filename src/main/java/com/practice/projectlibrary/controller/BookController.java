package com.practice.projectlibrary.controller;

import com.practice.projectlibrary.dto.request.BookRequest;
import com.practice.projectlibrary.dto.response.BookResponse;
import com.practice.projectlibrary.exception.BadRequestException;
import com.practice.projectlibrary.service.IBookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

  private final IBookService bookService;

  @GetMapping("/**")
  @ResponseStatus(HttpStatus.OK)
  public List<BookResponse> books() {
    return bookService.books();
  }

  @PostMapping("/add-list-book")
  @ResponseStatus(HttpStatus.OK)
  public List<BookResponse> addListBook(@RequestBody List<@Valid BookRequest> bookRequest) {
    return bookService.addListBook(bookRequest);
  }

  @PostMapping("/")
  @ResponseStatus(HttpStatus.OK)
  public BookResponse addBook(@RequestPart("file") MultipartFile file, @Valid @RequestPart("bookRequest") BookRequest bookRequest) {
    if (file.isEmpty()) {
      throw new BadRequestException("File image is mandatory");
    } else {
      return bookService.addBook(file, bookRequest);
    }
  }

  @PostMapping("/{slug}/{id}")
  @ResponseStatus(HttpStatus.OK)
  public BookResponse updateBook(@PathVariable("slug") @NotNull(message = "slug is mandatory") String slug, @PathVariable("id") @Positive(message = "Book Id greater than zero") Long id, @Valid @RequestBody BookRequest bookRequest) {
    return bookService.updateBook(slug, id, bookRequest);
  }

  @DeleteMapping("/{slug}/{id}")
  public BookResponse deleteBook(@PathVariable("slug") @NotNull(message = "slug is mandatory") String slug, @PathVariable("id") Long id) {
    return bookService.deleteBook(slug, id);
  }

  @GetMapping("/search/{slug}")
  @ResponseStatus(HttpStatus.OK)
  public BookResponse searchBook(@PathVariable("slug") @NotNull(message = "Slug is mandatory") String slug) {
    return bookService.searchBoook(slug);
  }


//  @GetMapping("/search/{slug}")
//  @ResponseStatus(HttpStatus.OK)
//  public List<BookResponse> search(@PathVariable("slug") @NotNull(message = "Slug is mandatory") String slug) {
//    return bookService.search(slug);
//  }
}

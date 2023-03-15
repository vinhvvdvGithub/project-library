package com.practice.projectlibrary.controller;

import com.practice.projectlibrary.dto.BookDTO;
import com.practice.projectlibrary.dto.request.BookRequest;

import com.practice.projectlibrary.dto.respone.BookRespone;


import com.practice.projectlibrary.entity.Book;
import com.practice.projectlibrary.exception.BadRequestException;

import com.practice.projectlibrary.service.IBookService;
import com.practice.projectlibrary.service.ICategorySevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private IBookService bookService;

    @Autowired
    private ICategorySevice categorySevice;


    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> books(){
        return bookService.books();
    }

    @PostMapping("/add-list-book")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> addBook(@RequestBody List<BookRequest> bookRequest){
        if(bookRequest.equals("")){
            throw new BadRequestException("Not found book request");
        }
        return bookService.addListBook(bookRequest);
    }

    @PostMapping("/{slug}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO updateBook(@PathVariable("slug") String slug, @PathVariable("id") Long id,@Valid @RequestBody BookRequest bookRequest){
        return bookService.updateBook(slug,id,bookRequest);
    }

    @DeleteMapping("/{slug}/{id}")
    public BookDTO deleteBook(@PathVariable("slug") String slug, @PathVariable("id") Long id){
        return bookService.deleteBook(slug,id);
    }



}

package com.practice.projectlibrary.service;

import com.practice.projectlibrary.dto.BookDTO;
import com.practice.projectlibrary.dto.request.BookRequest;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

public interface IBookService {

    //list book
    List<BookDTO> books();

    //add new book
    BookDTO addBook(MultipartFile file, BookRequest bookRequest);

    List<BookDTO> addListBook(List<BookRequest> bookRequest);
    //update book by slug && id
    BookDTO updateBook(String slug, Long id, BookRequest bookRequest);

    //delete book by slug && id
    BookDTO deleteBook(String slug, Long id);


}

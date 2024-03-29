package com.practice.projectlibrary.service;

import com.practice.projectlibrary.dto.request.BookRequest;
import com.practice.projectlibrary.dto.response.BookResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IBookService {

  //list book
  List<BookResponse> books();

  BookResponse searchBoook(String slug);

  //add new book
  BookResponse addBook(MultipartFile file, BookRequest bookRequest);

  List<BookResponse> addListBook(List<BookRequest> bookRequest);

  //update book by slug && id
  BookResponse updateBook(MultipartFile file,String slug, Long id, BookRequest bookRequest);

  //delete book by slug && id
  BookResponse deleteBook(String slug, Long id);

  //search
  List<BookResponse> search (String slug);

}

package com.practice.projectlibrary.common.Mapper;


import com.practice.projectlibrary.dto.request.BookRequest;
import com.practice.projectlibrary.dto.response.BookResponse;
import com.practice.projectlibrary.entity.Book;

public class BookMapper {

  private static BookMapper INSTANCE;

  public static BookMapper getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new BookMapper();
    }
    return INSTANCE;
  }

  //to book entity from request
  public Book toEntity(BookRequest bookRequest) {
    Book book = new Book();
    book.setBookTitle(bookRequest.getBookTitle());
    book.setAuthor(bookRequest.getAuthor());
    book.setDescription(bookRequest.getDescription());
    book.setSlug(bookRequest.getSlug());
    book.setQuantity(bookRequest.getQuantity());
    book.setPrice(bookRequest.getPrice());
    return book;

  }


  //to book response
  public BookResponse toResponse(Book book) {
    BookResponse bookResponse = new BookResponse();
    bookResponse.setBookTitle(book.getBookTitle());
    bookResponse.setAuthor(book.getAuthor());
    bookResponse.setDescription(book.getDescription());
    bookResponse.setSlug(book.getSlug());
    bookResponse.setImage(book.getImage());
    bookResponse.setQuantity(book.getQuantity());
    bookResponse.setPrice(book.getPrice());
    bookResponse.setCategoryName(book.getCategory().getCategoryName());

    return bookResponse;
  }
}

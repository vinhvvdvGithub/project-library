package com.practice.projectlibrary.common.Mapper;


import com.practice.projectlibrary.dto.BookDTO;
import com.practice.projectlibrary.dto.request.BookRequest;
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
    public Book toEntity(BookRequest  bookRequest) {
        Book book = new Book();
        book.setBookTitle(bookRequest.getBookTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setDescription(bookRequest.getDescription());
        book.setSlug(bookRequest.getSlug());
        book.setQuantity(bookRequest.getQuantity());
        book.setPrice(bookRequest.getPrice());
        return book;

    }


    //to book entity from dto
    public Book toEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setBookTitle(bookDTO.getBookTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setDescription(bookDTO.getDescription());
        book.setSlug(bookDTO.getSlug());
        book.setImage(bookDTO.getImage());
        book.setQuantity(bookDTO.getQuantity());
        book.setPrice(bookDTO.getPrice());
        return book;

    }

    //to book dto
    public BookDTO toDto(Book book){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookTitle(book.getBookTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setSlug(book.getSlug());
        bookDTO.setImage(book.getImage());
        bookDTO.setQuantity(book.getQuantity());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setCategoryName(book.getCategory().getCategoryName());

        return bookDTO;
    }
}

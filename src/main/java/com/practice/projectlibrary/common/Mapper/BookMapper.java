package com.practice.projectlibrary.common.Mapper;


import com.practice.projectlibrary.dto.BookDTO;
import com.practice.projectlibrary.entity.Book;

public class BookMapper {

    private static BookMapper INSTANCE;
    public static BookMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BookMapper();
        }
        return INSTANCE;
    }

    //to book entity
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

package com.practice.projectlibrary.dto.respone;

import com.practice.projectlibrary.dto.request.BookRequest;
import com.practice.projectlibrary.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRespone {

    private String bookTitle;
    private String author;

    private String description;
    private String slug;

    private String image;

    private Integer quantity;
    private Long price;


    public BookRespone(BookRequest bookRequest) {
        this.bookTitle=bookRequest.getBookTitle();
        this.author=bookRequest.getAuthor();
        this.description=bookRequest.getDescription();
        this.slug=bookRequest.getSlug();
        this.image=bookRequest.getImage();
        this.quantity=bookRequest.getQuantity();
        this.price=bookRequest.getPrice();
    }
}

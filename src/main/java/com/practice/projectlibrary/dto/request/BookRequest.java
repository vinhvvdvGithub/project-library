package com.practice.projectlibrary.dto.request;


import jakarta.validation.constraints.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

    @NotEmpty(message = "Book's title is mandatory")
    private String bookTitle;

    @NotEmpty(message = "Author is mandatory")
    private String author;

    @NotEmpty(message = "Description is mandatory")
    private String description;

    @NotEmpty(message = "Slug is mandatory")
    private String slug;


    private String image;

    @NotNull(message = "Quantity is mandatory")
    private Integer quantity;

    @NotNull(message = "Price is mandatory")
    private Long price;

    @NotNull(message = "CategoryId is mandatory")
    private Long categoryId;
}

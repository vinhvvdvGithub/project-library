package com.practice.projectlibrary.dto.request;

import com.practice.projectlibrary.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
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

    @NotEmpty(message = "Quantiry is mandatory")
    private Integer quantity;

    @NotEmpty(message = "Price is mandatory")
    private Long price;

    @NotEmpty(message = "CategoryId is mandatory")
    private Long categoryId;
}

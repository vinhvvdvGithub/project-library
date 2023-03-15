package com.practice.projectlibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {


    private String bookTitle;


    private String author;


    private String description;


    private String slug;


    private String image;


    private Integer quantity;


    private Long price;


    private String categoryName;
}

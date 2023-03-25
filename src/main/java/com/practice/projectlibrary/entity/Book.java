package com.practice.projectlibrary.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import jakarta.persistence.*;


@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity {

    @Column(name = "book_title")
    @NotBlank(message = "Book's title is mandatory")
    private String bookTitle;

    @Column(name = "author")
    @NotBlank(message = "Author's name is mandatory")
    private String author;

    @Column(name = "description")
    @NotBlank(message = "Description is mandatory")
    private String description;
    @Column(name = "slug")
    @NotBlank(message = "Slug is mandatory")
    private String slug;

    private String image;

    //    @NotBlank(message = "Quantity is mandatory")
    private Integer quantity;


    //    @NotBlank
    private Long price;


    private Boolean status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


}

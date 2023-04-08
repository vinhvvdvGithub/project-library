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
    private String bookTitle;

    @Column(name = "author")
    private String author;

    @Column(name = "description")
    private String description;
    @Column(name = "slug")
    private String slug;

    private String image;


    private Integer quantity;


    private Long price;


    private Boolean status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


}

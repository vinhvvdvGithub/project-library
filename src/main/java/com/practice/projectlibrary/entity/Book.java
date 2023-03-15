package com.practice.projectlibrary.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_title")
    @NotBlank(message = "Book's title is mandatory")
    private String bookTitle;

    @Column(name="author")
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


    @Column(name = "created_at")
//    @Temporal(value = TemporalType.TIMESTAMP)
    private Timestamp createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
//    @Temporal(value = TemporalType.TIMESTAMP)
    private Timestamp updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;



}

package com.practice.projectlibrary.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "status")
    private Boolean status;

    @Column(name="slug")
    private String slug;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Book> bookByCategory =new ArrayList<>();


    @Column(name = "created_at")
//    @Temporal(value = TemporalType.TIMESTAMP)
    private Timestamp createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at",nullable = true)
//    @Temporal(value = TemporalType.TIMESTAMP)
    private Timestamp updatedAt;

    @Column(name = "updated_by",nullable = true)
    private String updatedBy;

}

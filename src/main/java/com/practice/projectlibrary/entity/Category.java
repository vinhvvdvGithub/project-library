package com.practice.projectlibrary.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseEntity {
    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "slug")
    private String slug;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> bookByCategory = new ArrayList<>();
}

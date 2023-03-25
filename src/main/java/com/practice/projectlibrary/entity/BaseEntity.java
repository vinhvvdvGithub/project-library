package com.practice.projectlibrary.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;



import java.sql.Timestamp;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "created_date")
//    private Timestamp createdDate;

    @Column(name = "create_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "create_by")
    private String createdBy;

//    @Column(name = "updated_date")
//    private Timestamp updatedDate;

    @Column(name = "update_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Column(name = "update_by")
    private String updatedBy;
}

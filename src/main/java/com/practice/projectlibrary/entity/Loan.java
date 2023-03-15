package com.practice.projectlibrary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.sql.Timestamp;

@Entity
@Table(name = "loans")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id")
    private Long bookId;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    private Integer quantity;

    private Boolean active;

    private String status;

    @Column(name = "date_of_checkout")
    private Timestamp dateOfCheckout;

    @Column(name = "date_due")
    private Timestamp dataDue;

    @Null
    @Column(name="date_returned",nullable = true)
    private Timestamp dateReturned;

    @CreationTimestamp
    @Column(name = "created_at",updatable = false)
//    @Temporal(value = TemporalType.TIMESTAMP)
    private Timestamp createdAt;
    @Column(name = "created_by")
    private String createdBy;


}

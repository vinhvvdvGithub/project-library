package com.practice.projectlibrary.entity;

import jakarta.validation.constraints.Null;
import lombok.*;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "loans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Loan extends BaseEntity {


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
    @Column(name = "date_returned", nullable = true)
    private Timestamp dateReturned;

}

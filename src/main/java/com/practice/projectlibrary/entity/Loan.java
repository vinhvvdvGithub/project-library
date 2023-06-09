package com.practice.projectlibrary.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	private Integer quantity;

	private String status;

	@Column(name = "log_renew", nullable = true,columnDefinition = "integer default 0")
	private Integer logRenew;

	@Column(name = "date_of_checkout")
	private Timestamp dateOfCheckout;

	@Column(name = "date_due")
	private Timestamp dueDate;

	@Column(name = "date_returned", nullable = true)
	private Timestamp dateReturned;

}

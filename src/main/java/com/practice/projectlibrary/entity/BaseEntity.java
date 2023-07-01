package com.practice.projectlibrary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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


	@Column(name = "active")
	private Boolean active;

	@Column(name = "created_date")
	@CreationTimestamp
	private Timestamp createdDate;


	@Column(name = "create_by")
	private String createdBy;

	@Column(name = "updated_date")
	@UpdateTimestamp
	private Timestamp updatedDate;


	@Column(name = "update_by")
	private String updatedBy;
}

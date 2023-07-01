package com.practice.projectlibrary.entity;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailDetails {

	@Email
	private String recipient;
	private String msgBody;
	private String subject;
	private String attachment;
}

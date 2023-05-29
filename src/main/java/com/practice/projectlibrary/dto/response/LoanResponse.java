package com.practice.projectlibrary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanResponse {
  private Long bookId;

  private Long userId;

  private Integer quantity;

  private Boolean active;

  private String status;

  private Timestamp dateOfCheckout;

  private Timestamp dataDue;

  private Timestamp dateReturned;
}

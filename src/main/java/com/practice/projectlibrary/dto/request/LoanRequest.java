package com.practice.projectlibrary.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequest {


  @NotNull(message = "Book id is mandatory")
  private Long bookId;


  @NotNull(message = "Quantity is mandatory")
  @Min(value = 0, message = "Value should be greater then then equal to 0")
  @Max(value = 3, message = "Value should be less then then equal to 3")
//    @NotEmpty(message = "Quantity is mandatory")
  private Integer quantity;


}

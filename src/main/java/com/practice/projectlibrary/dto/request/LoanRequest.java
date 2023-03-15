package com.practice.projectlibrary.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequest {

    @NotEmpty(message = "Book id is mandatory")
    private Long bookId;

    @NotEmpty(message = "User id is mandatory")
    private Long userId;

    @NotEmpty(message = "Quantity is mandatory")
    private Integer quantity;


}

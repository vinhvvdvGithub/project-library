package com.practice.projectlibrary.dto.request;

import lombok.*;

import jakarta.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequest {


    @NotNull(message = "Book id is mandatory")
    private Long bookId;


    @NotNull(message = "User id is mandatory")
    private Long userId;

    @NotNull(message = "Quantity is mandatory")
//    @NotEmpty(message = "Quantity is mandatory")
    private Integer quantity;


}

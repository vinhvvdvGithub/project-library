package com.practice.projectlibrary.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
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

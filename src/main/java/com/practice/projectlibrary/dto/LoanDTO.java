package com.practice.projectlibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {

    @NotEmpty(message = "Book id is mandatory")
    private Long bookId;

    @NotEmpty(message = "User id is mandatory")
    private Long userId;

    @NotEmpty(message = "Quantity is mandatory")
    private Integer quantity;

    private Boolean active;

    private String status;

    private Timestamp dateOfCheckout;

    private Timestamp dataDue;

    private Timestamp dateReturned;


}

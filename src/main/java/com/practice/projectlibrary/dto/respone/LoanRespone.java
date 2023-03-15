package com.practice.projectlibrary.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanRespone {

    private Long bookId;
    private Long userId;

    private Integer quantity;

    private Boolean active;

    private String status;

    private Timestamp dateOfCheckout;

    private Timestamp dataDue;

    private Timestamp dateReturned;

}

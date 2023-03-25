package com.practice.projectlibrary.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;




@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponeObject {
    private HttpStatus statusCode;
    private String message;
    private Object data;
}

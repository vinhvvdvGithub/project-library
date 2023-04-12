package com.practice.projectlibrary.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;




@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObject {
    private HttpStatus statusCode;
    private String message;
    private Object data;
}

package com.practice.projectlibrary.dto.respone;

import lombok.*;
import org.springframework.http.HttpStatus;




@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponeObject {
    private HttpStatus statusCode;
    private String message;
    private Object data;
}
